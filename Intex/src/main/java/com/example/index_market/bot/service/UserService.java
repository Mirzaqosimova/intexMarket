package com.example.index_market.bot.service;

import com.example.index_market.bot.payload.ProductDto;
import com.example.index_market.bot.payload.SendOwnPhoto;
import com.example.index_market.bot.utils.BotStatus;
import com.example.index_market.bot.utils.CallbackName;
import com.example.index_market.bot.utils.PageSizeException;
import com.example.index_market.entity.address.Address;
import com.example.index_market.entity.order.Order;
import com.example.index_market.entity.product.Product;
import com.example.index_market.entity.user.AuthUser;
import com.example.index_market.entity.user.BotUser;
import com.example.index_market.repository.bot.BotUserRepository;
import com.example.index_market.repository.order.OrderRepository;
import com.example.index_market.repository.product.ProductRepository;
import com.example.index_market.repository.user.UserRepository;
import com.example.index_market.type.user.Role;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    private final BotUserRepository botUserRepository;
    private final AdminService adminService;
    private final ProductBotService productBotService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public static final String PHONENUMBER_REGEX = "\\+998[0-9]{9}";

    public List<SendOwnPhoto> startCommand(Update update) throws PageSizeException {

        Long chatId = getChatId(update);
        BotUser user = creatorGetUser(chatId);

        user.setBotStatus(BotStatus.GET_ALL_PRODUCTS);
        saveBotUserChanges(user);

        return sendProducts(user);
    }

    public List<SendOwnPhoto> sendProducts(BotUser botUser) throws PageSizeException {
        ProductDto usersPageble = productBotService.getUsersPageble(botUser);
        String check = "";
        if (usersPageble.getCurrentlyPage() == usersPageble.getTotalPage()) {
            check = CallbackName.PRODUCT_LAST.name();
        } else if (usersPageble.getCurrentlyPage() == 1) {
            check = CallbackName.PRODUCT_NEXT.name();
        }
        int i = 0;
        for (String productId : usersPageble.getSendOwnPhotoMap().keySet()) {
            i++;
            SendOwnPhoto sendOwnPhoto = usersPageble.getSendOwnPhotoMap().get(productId);
            if (i == 5) {
                sendOwnPhoto.setReply_markup(generateInlineKeyboardForProductList(check, productId));
            } else {
                sendOwnPhoto.setReply_markup(generateInlineKeyboardForProductList(null, productId));

            }
        }

        return  usersPageble.getSendOwnPhotoMap().values().stream().toList();
    }

    public InlineKeyboardMarkup generateInlineKeyboardForProductList(String isLast, String productId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton row1Button1 = new InlineKeyboardButton();
        row1Button1.setText("Buyurtma berish / ЗАКАЗАТЬ");
        row1Button1.setCallbackData(productId);
        row1.add(row1Button1);
        rowList.add(row1);

        if (isLast == null) {
            markup.setKeyboard(rowList);
            return markup;
        }
        row1Button1 = new InlineKeyboardButton();
        row1 = new ArrayList<>();
        if (isLast.equals(CallbackName.PRODUCT_LAST.name())) {
            row1Button1.setText("BACK");
            row1Button1.setCallbackData(CallbackName.PRODUCT_LAST.name());
        } else if (isLast.equals(CallbackName.PRODUCT_NEXT.name())) {
            row1Button1.setText("NEXT");
            row1Button1.setCallbackData(CallbackName.PRODUCT_NEXT.name());

        }
        row1.add(row1Button1);
        rowList.add(row1);
        markup.setKeyboard(rowList);
        return markup;

    }

    public BotUser creatorGetUser(Long id) {
        Optional<BotUser> byChatId = botUserRepository.findByChatId(id);
        if (byChatId.isPresent()) {
            if(byChatId.get().getAuthUser() == null){
                return byChatId.get();
            }

            byChatId.get().getAuthUser().setRole(Role.USER);
          return    botUserRepository.save(byChatId.get());
        }
        BotUser botUser = BotUser.builder()
                .botStatus(BotStatus.START)
                .chatId(id)
                .currentlyPage(1).build();
        return botUserRepository.save(botUser);
    }

    private Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

    private void saveBotUserChanges(BotUser botUser) {
        Optional<BotUser> byId = botUserRepository.findById(botUser.getId());
        if (byId.isPresent()) {
            BotUser botUser1 = byId.get();
            botUser1.setBotStatus(botUser.getBotStatus());
            botUserRepository.save(botUser1);
        }
    }

    public List<SendOwnPhoto> getProductByNextOrLast(Update update, boolean b) throws PageSizeException {
        BotUser botUser = creatorGetUser(getChatId(update));
        if (b) {
            botUser.setCurrentlyPage(botUser.getCurrentlyPage() + 1);
        } else {
            if(botUser.getCurrentlyPage() > 1) {
                botUser.setCurrentlyPage(botUser.getCurrentlyPage() - 1);
            }
        }
        saveBotUserChanges(botUser);

        return sendProducts(botUser);
    }

    public SendMessage addOrder(Update update) {
        String data = update.getCallbackQuery().getData();
        Optional<Product> byId = productRepository.findById(data);
        BotUser botUser = creatorGetUser(getChatId(update));
        if (byId.isPresent()) {
            botUser.setProduct(byId.get());
            botUser.setBotStatus(BotStatus.ORDER_GET_PHONE_NUMBER);
            saveBotUserChanges(botUser);
            SendMessage sendMessage = new SendMessage(botUser.getChatId().toString(), "Telefon raqamingizni kiritin");
            sendMessage.setReplyMarkup(sendPhoneNumber());
            return sendMessage;
        }
        return new SendMessage(botUser.getChatId().toString(), "OOPS something went wrong");
    }


    public ReplyKeyboardMarkup sendPhoneNumber() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton row1Button1 = new KeyboardButton();
        row1Button1.setText("Raqamingizni jo'nating  ☎");
        row1Button1.setRequestContact(true);
        row1.add(row1Button1);
        rowList.add(row1);

        markup.setKeyboard(rowList);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        return markup;
    }

    public SendMessage setPhoneNumber(Update update, boolean b) {
        BotUser botUser = creatorGetUser(getChatId(update));
        AuthUser authUser = new AuthUser();
        String phoneNumber;

        if (b) {
            phoneNumber = update.getMessage().getContact().getPhoneNumber();
        } else {
            phoneNumber = update.getMessage().getText();
            if (!isPhoneNumberValid(phoneNumber)) {
                return new SendMessage(botUser.getChatId().toString(), "Xato telefon raqami kiritldi qayta urining!");
            }
        }
        Optional<AuthUser> byPhone = userRepository.findByPhone(phoneNumber);
        if(byPhone.isPresent()){
            authUser = byPhone.get();
        } else {
            authUser.setPhone(phoneNumber);
        }
        AuthUser save = userRepository.save(authUser);
        botUser.setAuthUser(save);
        botUser.setBotStatus(BotStatus.ORDER_GET_NAME);
        saveBotUserChanges(botUser);
        return new SendMessage(botUser.getChatId().toString(), "Ismingizni kiriting:");

    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONENUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public SendMessage setUsername(Update update) {
        String name = update.getMessage().getText();
        BotUser botUser = creatorGetUser(getChatId(update));
        if (botUser.getAuthUser() != null) {
            botUser.getAuthUser().setRole(Role.USER);
            botUser.getAuthUser().setName(name);
            botUser.setBotStatus(BotStatus.ORDER_GET_ADDRESS);
            saveBotUserChanges(botUser);
            return new SendMessage(botUser.getChatId().toString(), "Adresngizni toliq kiriting");
        }
        return new SendMessage(botUser.getChatId().toString(), "Xatolik");
    }

    public SendMessage setAdress(Update update) {
        String address = update.getMessage().getText();
        BotUser botUser = creatorGetUser(getChatId(update));
        if (botUser.getAuthUser() != null && botUser.getProduct() != null) {
            LocalDateTime now = LocalDateTime.now();
            Order order = new Order();
            Address address1 = new Address();
            address1.setFullAddress(address);
            order.setUser(botUser.getAuthUser());
            order.setAddress(address1);
            order.setArrived(false);
            order.setProduct(botUser.getProduct());
            order.setTime(now);
            orderRepository.save(order);
            botUser.setBotStatus(BotStatus.GET_ALL_PRODUCTS);

            SendMessage sendMessage = new SendMessage(botUser.getChatId().toString(), "Muvaffaqqiyatli order berdingiz");
            sendMessage.setReplyMarkup(generateInlineKeyboardForProduct());
            adminService.sentOrderToAdmin(order);
            return sendMessage;
        }
        return new SendMessage(botUser.getChatId().toString(), "Xatolik");

    }

    public InlineKeyboardMarkup generateInlineKeyboardForProduct() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton row1Button1 = new InlineKeyboardButton();
        row1Button1.setText("Productlar royxati");
        row1Button1.setCallbackData(CallbackName.SHOW_PRODUCT.name());

        row1.add(row1Button1);
        rowList.add(row1);
        markup.setKeyboard(rowList);
        return markup;
    }


}

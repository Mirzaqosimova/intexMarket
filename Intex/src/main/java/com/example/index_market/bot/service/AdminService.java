package com.example.index_market.bot.service;

import com.example.index_market.bot.feign.TelegramFeign;
import com.example.index_market.bot.utils.BotStatus;
import com.example.index_market.entity.order.Order;
import com.example.index_market.entity.user.AuthUser;
import com.example.index_market.entity.user.BotUser;
import com.example.index_market.repository.bot.BotUserRepository;
import com.example.index_market.repository.user.UserRepository;
import com.example.index_market.type.user.Role;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    @Autowired
    TelegramFeign telegramFeign;
    private final BotUserRepository botUserRepository;
    public SendMessage login(Update update) {
        String message = update.getMessage().getText();
        BotUser botUser = creatorGetUser(getChatId(update));
        Optional<AuthUser> authUserByPassword = userRepository.findAuthUserByPassword(message);
        if(authUserByPassword.isPresent()){
            botUser.getAuthUser().setRole(Role.ADMIN);
            botUser.setBotStatus(BotStatus.ADMIN);
            saveBotUserChanges(botUser);
            return new SendMessage(botUser.getChatId().toString(),"siz admin bo'limidasiz qilingar orderlarni shu page orqali korib turishingiz mumkin");
        }
         return new SendMessage(botUser.getChatId().toString(),"Parolda xatolik");

    }

    public SendMessage startAdmin(Update update){
        BotUser botUser = creatorGetUser(getChatId(update));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(botUser.getChatId().toString());
        botUser.setBotStatus(BotStatus.SEND_PASSWORD);
        saveBotUserChanges(botUser);
        sendMessage.setText("Parol jonating!");
        return sendMessage;
    }
    private void saveBotUserChanges(BotUser botUser) {
        Optional<BotUser> byId = botUserRepository.findById(botUser.getId());
        if (byId.isPresent()) {
            BotUser botUser1 = byId.get();
            botUser1.setBotStatus(botUser.getBotStatus());
            botUserRepository.save(botUser1);
        }
    }
    public BotUser creatorGetUser(Long id) {
        Optional<BotUser> byChatId = botUserRepository.findByChatId(id);
        if (byChatId.isPresent()) {
            return byChatId.get();
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

    public void sentOrderToAdmin(Order order){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        List<BotUser> allByAuthUserRole = botUserRepository.findAllByAuthUserRole(Role.ADMIN);
        if(!allByAuthUserRole.isEmpty()){
            for (BotUser user:allByAuthUserRole  ) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(user.getChatId().toString());
                sendMessage.setText(
                        "Klient nomi: "+order.getUser().getName()+
                                "\nTelefon raqami: "+order.getUser().getPhone()+
                                "\nProduct shakli: "+order.getProduct().getFrameUz()+"/"+order.getProduct().getFrameRu()+
                                "\nNarxi: "+ order.getProduct().getDisPrice() +
                                "\nAddres: "+order.getAddress().getFullAddress()+
                                "\nVaqt: "+dtf.format(order.getTime()) );
                telegramFeign.sendMessageToUser(sendMessage);
            }
        }

    }
}

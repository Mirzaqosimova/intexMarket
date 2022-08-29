package com.example.index_market.bot.service;

import com.example.index_market.bot.feign.TelegramFeign;
import com.example.index_market.bot.payload.SendOwnPhoto;
import com.example.index_market.bot.utils.BotConstant;
import com.example.index_market.bot.utils.BotStatus;
import com.example.index_market.bot.utils.CallbackName;
import com.example.index_market.bot.utils.PageSizeException;
import com.example.index_market.entity.user.BotUser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class BotService {


     @Autowired
     AdminService adminService;
    @Autowired
    UserService userService;

    @Autowired
    TelegramFeign telegramFeign;

    public void getUpdates(Update update) throws PageSizeException {

        if (update.hasMessage()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            BotUser botUser = userService.creatorGetUser(chatId);
            if (update.getMessage().hasContact()) {
                SendMessage sendMessage = userService.setPhoneNumber(update, true);

                telegramFeign.sendMessageToUser(sendMessage);
            } else {
                    switch (message) {
                        case BotConstant.ADMIN_PAGE -> {
                            SendMessage sendMessagec = adminService.startAdmin(update);
                            telegramFeign.sendMessageToUser(sendMessagec);
                        }

                        case BotConstant.START -> {
                            List<SendOwnPhoto> productDtos = userService.startCommand(update);
                            for (SendOwnPhoto s :
                                    productDtos) {
                                telegramFeign.sendPhotoToUser(s);
                            }
                        }

                        default -> {


                            if (botUser.getBotStatus().name().equals(BotStatus.ORDER_GET_PHONE_NUMBER.name())) {
                                SendMessage sendMessage = userService.setPhoneNumber(update, false);
                                telegramFeign.sendMessageToUser(sendMessage);
                            } else if (botUser.getBotStatus().equals(BotStatus.ORDER_GET_NAME)) {
                                SendMessage sendMessage = userService.setUsername(update);
                                telegramFeign.sendMessageToUser(sendMessage);
                            } else if (botUser.getBotStatus().equals(BotStatus.ORDER_GET_ADDRESS)) {
                                SendMessage sendMessage = userService.setAdress(update);
                                telegramFeign.sendMessageToUser(sendMessage);
                            }else if(botUser.getBotStatus().equals(BotStatus.SEND_PASSWORD)){
                                SendMessage login = adminService.login(update);
                                telegramFeign.sendMessageToUser(login);
                            }else {
                                SendMessage sendMessage = new SendMessage(chatId.toString(), "Bunday amal mavjud emas");
                                telegramFeign.sendMessageToUser(sendMessage);
                            }
                        }
                    }
            }
        }
else if(update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
    if(data.equals(CallbackName.PRODUCT_NEXT.name())){
        List<SendOwnPhoto> productByNextOrLast = userService.getProductByNextOrLast(update,true);
        for (SendOwnPhoto s :
                productByNextOrLast) {
            telegramFeign.sendPhotoToUser(s);
        }
    }else if(data.equals(CallbackName.PRODUCT_LAST.name())){
        List<SendOwnPhoto> productByNextOrLast = userService.getProductByNextOrLast(update,false);
        for (SendOwnPhoto s :
                productByNextOrLast) {
            telegramFeign.sendPhotoToUser(s);
        }

    } else if(data.equals(CallbackName.SHOW_PRODUCT.name())){
                List<SendOwnPhoto> productDtos = userService.startCommand(update);
                for (SendOwnPhoto s :
                        productDtos) {
                    telegramFeign.sendPhotoToUser(s);
                }
            }
            else {
        SendMessage sendMessage = userService.addOrder(update);
        telegramFeign.sendMessageToUser(sendMessage);
    }
        }
    }

}

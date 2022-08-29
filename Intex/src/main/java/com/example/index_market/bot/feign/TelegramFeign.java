package com.example.index_market.bot.feign;


import com.example.index_market.bot.payload.ProductDto;
import com.example.index_market.bot.payload.SendOwnPhoto;
import com.example.index_market.bot.utils.BotConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@FeignClient(url = BotConstant.TELEGRAM_BASE_URL_WITHOUT_BOT,
name = "TelegramFeign")
public interface TelegramFeign {

    @PostMapping("/bot" + BotConstant.BOT_TOKEN+"/sendMessage")
    void  sendMessageToUser(@RequestBody SendMessage sendMessage);
//    https://api.telegram.org/bot"+token+"/sendPhoto?chat_id="+chat_id+"&photo="+pic
    @PostMapping(path = "/bot" + BotConstant.BOT_TOKEN+"/sendPhoto")
    void sendPhotoToUser(@RequestBody SendOwnPhoto sendOwnPhoto);

}

package com.example.index_market.bot.controller;


import com.example.index_market.bot.service.BotService;
import com.example.index_market.bot.utils.PageSizeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class WebhookController {
     @Autowired
     BotService botService;

    @PostMapping
    public void getUpdates(@RequestBody Update update) throws PageSizeException {
        System.out.println(update);
        botService.getUpdates(update);
    }
}

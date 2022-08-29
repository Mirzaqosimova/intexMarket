package com.example.index_market.bot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendOwnPhoto {
    private  String chat_id;
    private  String photo;
    private  String caption;
    private InlineKeyboardMarkup reply_markup;
}

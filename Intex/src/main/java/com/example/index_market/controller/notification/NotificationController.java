package com.example.index_market.controller.notification;

import com.example.index_market.dto.message.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/orders")
    @MessageMapping("/send-order")
    public boolean sendMessagestoOrders(MessageDto messageDto) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(
                messageDto.getReceiverId(), "queue/messages", messageDto);
   return true;
    }

    @PostMapping("/consultation")
    @MessageMapping("/send-consultation")
    public void sendMessagestoConsultation(MessageDto messageDto) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(
                messageDto.getReceiverId(), "queue/messages", messageDto);
    }
}

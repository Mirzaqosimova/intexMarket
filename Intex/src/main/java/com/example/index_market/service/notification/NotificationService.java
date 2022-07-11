package com.example.index_market.service.notification;

import com.example.index_market.dto.consultation.ConsultationSelectAndUpdateDto;
import com.example.index_market.dto.message.MessageDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserServiceImpl userService;

    public boolean sendNotification(Object objact, boolean isOrderDto) {
        String adminId = userService.getAdminId();
        if (adminId == null) {
            return false;
        }
        String url;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        MessageDto messageDto = MessageDto.builder()
                .receiverId(adminId)
                .build();
        if (isOrderDto) {
            messageDto.setObject(objact);
            url = "http://localhost:8080/api/notification/orders";
        } else {
            messageDto.setObject(objact);
            url = "http://localhost:8080/api/notification/consultation";
        }
        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto, httpHeaders);
        restTemplate.postForObject(url, request, Void.class);

        return true;

    }


}

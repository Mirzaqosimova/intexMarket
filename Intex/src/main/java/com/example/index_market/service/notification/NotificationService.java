package com.example.index_market.service.notification;

import com.example.index_market.dto.message.MessageDto;
import com.example.index_market.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final String URL_FOR_ORDER = "http://localhost:8080/api/notification/orders";
    private final String URL_FOR_CONSULTATION = "http://localhost:8080/api/notification/consultation";
    private final UserServiceImpl userService;

    public boolean sendNotification(Object object, boolean isOrder){
        String adminId = userService.getAdminId();
          if(adminId == null){
              return false;
          }
        RestTemplate restTemplate = new RestTemplate();
        MessageDto messageDto = MessageDto.builder()
                .receiverId(adminId)
                .object(object)
                .build();

        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto);
        ResponseEntity<Void> response;
if(isOrder){
    response  = restTemplate.exchange(URL_FOR_ORDER, HttpMethod.POST, request, Void.class);

}else {
     response = restTemplate.exchange(URL_FOR_CONSULTATION, HttpMethod.POST, request, Void.class);

}
        return response.getStatusCode() == HttpStatus.OK;

    }



}

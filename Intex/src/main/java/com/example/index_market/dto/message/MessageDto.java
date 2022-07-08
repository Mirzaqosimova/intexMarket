package com.example.index_market.dto.message;

import com.example.index_market.dto.consultation.ConsultationSelectAndUpdateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.entity.consultant.Consultant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {

    private String receiverId;

    private OrderDto orderDto;

    private ConsultationSelectAndUpdateDto consultDto;






}

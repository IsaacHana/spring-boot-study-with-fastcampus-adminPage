package com.example.adminpage.model.network.response;

import com.example.adminpage.model.enumclass.OrderDetailType;
import com.example.adminpage.model.enumclass.OrderType;
import com.example.adminpage.model.enumclass.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {
    private Long id;

    private OrderDetailType status;

    private OrderType orderType;

    private String revAddress;

    private String revName;

    private PaymentType paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;

    private List<OrderDetailApiResponse> orderDetailApiResponses;
}

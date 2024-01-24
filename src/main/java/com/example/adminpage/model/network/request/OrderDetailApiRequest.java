package com.example.adminpage.model.network.request;

import com.example.adminpage.model.enumclass.OrderDetailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiRequest {
    private Long id;

    private OrderDetailType status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;
}

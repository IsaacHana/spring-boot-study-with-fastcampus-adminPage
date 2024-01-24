package com.example.adminpage.model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
@Getter
public enum OrderDetailType {

    ORDERING(1, "주문중"),
    COMPLETE(2, "배송완료"),
    CONFIRM(3, "주문확정");

    private final Integer id;
    private final String orderType;

    private static final Random random = new Random();

    public static OrderDetailType randomOrderDetailType() {
        OrderDetailType[] orderDetailTypes = values();
        return orderDetailTypes[random.nextInt(orderDetailTypes.length)];
    }
}

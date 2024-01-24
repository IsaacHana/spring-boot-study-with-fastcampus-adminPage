package com.example.adminpage.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum PaymentType {

    BANK_TRANSFER(1, "은행 송금"),
    CARD(2, "신용카드 결제"),
    CHECK_CARD(3, "체크카드 결제");

    private final Integer id;
    private final String typeName;

    private static final Random random = new Random();

    public static PaymentType randomType() {
        PaymentType[] paymentTypes = values();
        return paymentTypes[random.nextInt(paymentTypes.length)];
    }
}

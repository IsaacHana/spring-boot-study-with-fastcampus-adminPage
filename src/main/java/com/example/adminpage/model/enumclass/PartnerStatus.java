package com.example.adminpage.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartnerStatus {
    REGISTERED(0, "등록", "파트너 등록 상태"),
    UNREGISTERED(1, "해지", "파트너 해지 상태");

    private final Integer id;

    private final String title;

    private final String description;
}

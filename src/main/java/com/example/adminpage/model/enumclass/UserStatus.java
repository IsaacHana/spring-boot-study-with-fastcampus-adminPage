package com.example.adminpage.model.enumclass;

public enum UserStatus {
    REGISTERED(0, "등록", "사용자 등록 상태"),
    UNREGISTERED(1, "해지", "사용자 해지 상태");

    private final Integer id;
    private final String title;
    private final String description;

    UserStatus(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}

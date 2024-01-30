package com.example.adminpage.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {
    FOOD(0, "식품", "food"),
    HOME_DECORATION(1, "홈 인테리어", "home"),
    SPORTS(2, "스포츠/레저", "sport"),
    CLOTHES(3, "의류", "clothes"),
    COMPUTER(4, "컴퓨터/가전", "computer"),
    COSMETICS(5, "화장품", "cosmetic");

    private final Integer id;
    private final String name;
    private final String filePrefix;
}

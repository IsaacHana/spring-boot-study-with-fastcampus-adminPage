package com.example.adminpage.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {
    FOOD(0, "식품"),
    HOME_DECORATION(1, "홈인테리어"),
    SPORTS(2, "스포츠/레저"),
    BOOK(3, "도서/음반/DVD"),
    HOBBY(4, "완구/취미"),
    HEALTH(5, "헬스/건상식품");

    private final Integer id;
    private final String name;
}

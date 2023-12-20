package com.example.adminpage.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
    // response와 request를 구분하는 이유
    // request는 데이터가 평문으로 들어오지만
    // response로 내려줄 때에는 password 같은 부분을 암호화 해서 내려준다던지
    // 값의 변화를 주어 내려줄 수 있기 때문에 용이하다.

    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}

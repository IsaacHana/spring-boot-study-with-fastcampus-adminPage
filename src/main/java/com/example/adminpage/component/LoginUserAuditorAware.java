package com.example.adminpage.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

// 로그인한 사용자를 감시하는 역할의 구현체
@Component
public class LoginUserAuditorAware implements AuditorAware<String> { // <String> 사용자의 이름으로 받을 거라서 ex) createdBy : "isaac"
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer"); // 아직 로그인서비스가 없으로 default 설정
    }
}

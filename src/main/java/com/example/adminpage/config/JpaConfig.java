package com.example.adminpage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing // auditing을 활성화 하겠다.
public class JpaConfig {
}

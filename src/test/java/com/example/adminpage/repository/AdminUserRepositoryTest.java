package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdminUserRepositoryTest extends AdminPageApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        // AuditorAware 적용 테스트
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
    }


    @DisplayName("AuditorAware 적용 테스트")
    @Test
    public void createWithAuditorAware() {
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        // createdAt과 createdBy 은 auditorAware 에서 감시한다.

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
    }
    @Test
    public void read(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);

        Assertions.assertTrue(adminUser.isPresent());
    }
}

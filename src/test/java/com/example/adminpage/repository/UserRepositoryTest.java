package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.User;
import com.example.adminpage.model.enumclass.UserStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminPageApplicationTests {

    // Dependency Injection (DI) -> Singleton 패턴
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test01";
        String password = "Test01";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        // insert into user (account,created_at,created_by,email,password,phone_number,registered_at,status,unregistered_at,updated_at,updated_by)
        // values (?,?,?,?,?,?,?,?,?,?,?);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(user);
    }

    @Test
    @Transactional
    public void read() {
        // select *
        // from user u1_0
        // where u1_0.phone_number=?
        // order by u1_0.id desc limit ?
        String phoneNumber = "010-1111-2222";
        Optional<User> user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            Assertions.assertEquals(selectUser.getPhoneNumber(), phoneNumber);

            selectUser.getOrderGroups().forEach(orderGroup -> {
                System.out.println("-------------주문 묶음--------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("-------------주문 상세--------------");
                orderGroup.getOrderDetails().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartnerCompany().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartnerCompany().getCategory().getTitle());

                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getPartnerCompany().getCallCenter());

                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착 예정 일자 : " + orderDetail.getArrivalDate());
                });
            });
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            // 자바 빈 패턴
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            // chaining 으로 깔끔하게 표현
            selectUser.setAccount("aaaa")
                    .setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 테스트시 데이터를 롤백 시켜줌 
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent()); // false

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // false
    }
}
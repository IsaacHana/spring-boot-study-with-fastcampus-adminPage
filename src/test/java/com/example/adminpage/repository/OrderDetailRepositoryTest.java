package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends AdminPageApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());
        // 누가
//        orderDetail.setUserId(1L);

        // 어떤 상품
//        orderDetail.setItemId(1L);

//        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

//        Assertions.assertNotNull(newOrderDetail);
    }
}
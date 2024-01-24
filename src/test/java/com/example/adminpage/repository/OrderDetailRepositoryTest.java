package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.OrderDetail;
import com.example.adminpage.model.enumclass.OrderDetailType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends AdminPageApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus(OrderDetailType.ORDERING);
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900_000)); // double 보단 BigDecimal 이 깔끔, 값의 엄청난 정확도가 요구 되는 것이 아니라면..
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");
//        orderDetail.setItemId(1L); Long -> Item
//        orderDetail.setOrderGroupId(1L); // Long -> OrderGroup

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test
    public void read() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        Assertions.assertTrue(orderDetail.isPresent());
    }
}
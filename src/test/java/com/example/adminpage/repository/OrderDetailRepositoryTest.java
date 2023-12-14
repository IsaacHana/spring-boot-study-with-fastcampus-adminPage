package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.Item;
import com.example.adminpage.model.entity.OrderDetail;
import com.example.adminpage.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends AdminPageApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {

        OrderDetail orderDetail = new OrderDetail();
        Optional<User> user = userRepository.findById(1L);
        Optional<Item> item = itemRepository.findById(1L);

        orderDetail.setOrderAt(LocalDateTime.now());
        // 누가
        user.ifPresent(orderDetail::setUser);
        // 어떤 상품
        item.ifPresent(orderDetail::setItem);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assertions.assertNotNull(newOrderDetail);
    }
}
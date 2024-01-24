package com.example.adminpage.sample;

import com.example.adminpage.component.LoginUserAuditorAware;
import com.example.adminpage.config.JpaConfig;
import com.example.adminpage.model.entity.Item;
import com.example.adminpage.model.entity.OrderDetail;
import com.example.adminpage.model.entity.OrderGroup;
import com.example.adminpage.model.entity.User;
import com.example.adminpage.model.enumclass.OrderDetailType;
import com.example.adminpage.model.enumclass.OrderType;
import com.example.adminpage.model.enumclass.PaymentType;
import com.example.adminpage.repository.ItemRepository;
import com.example.adminpage.repository.OrderDetailRepository;
import com.example.adminpage.repository.OrderGroupRepository;
import com.example.adminpage.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("OrderDetailSample 생성")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OrderDetailSample {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void createOrder() {

        List<User> userList = userRepository.findAll();

        for (int j = 0; j < 1; j++) {
            User user = userList.get(j);
            item(user);
        }


        userList.forEach(user -> {
            int orderCount = random.nextInt(10) + 1;
            for (int i = 0; i < orderCount; i++) {
                item(user);
            }
        });


    }


    private void item(User user) {
        double totalAmount = 0;

        List<Item> items = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();
        List<OrderDetail> orderHistoryDetails = new ArrayList<>();

        int itemCount = random.nextInt(10) + 1;

        for (int i = 0; i < itemCount; i++) {
            // db에 아이템 갯수가 총 500개 ( * 자신의 샘플에 맞추세요 )
            int itemNumber = random.nextInt(405) + 1;
            int quantity = random.nextInt(10) + 1;

            Item item = itemRepository.findById((long) itemNumber).get();
            totalAmount += item.getPrice().doubleValue() * quantity;
            items.add(item);
            quantityList.add(quantity);
        }

        OrderDetailType status = OrderDetailType.randomOrderDetailType();
        PaymentType paymentType = PaymentType.randomType();

        int t = random.nextInt(2) + 1;
        OrderType type = t == 1 ? OrderType.ALL : OrderType.EACH;

        String[] address = {"경기도 분당구 판교역로", "경기도 안산시 선부광장북로", "서울시 마포구 합정", "경기도 일산", "서울시 강남구"};
        OrderGroup orderGroup = OrderGroup.builder()
                .user(user)
                .status(status)
                .orderType(type)
                .revAddress(address[random.nextInt(address.length)])
                .revName(user.getEmail())
                .paymentType(paymentType)
                .totalPrice(BigDecimal.valueOf(totalAmount))
                .orderAt(getRandomDate())
                .totalQuantity(itemCount)
                .arrivalDate(getRandomDate().plusDays(3))
                .orderDetails(orderHistoryDetails)
                .build();

        orderGroupRepository.save(orderGroup);


        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Integer quantity = quantityList.get(i);

            OrderDetailType orderDetailStatus = OrderDetailType.randomOrderDetailType();

            OrderDetail orderDetail = OrderDetail.builder()
                    .orderGroup(orderGroup)
                    .item(item)
                    .quantity(quantity)
                    .totalPrice(BigDecimal.valueOf(item.getPrice().doubleValue() * quantity))
                    .arrivalDate(type.equals(OrderType.ALL) ? orderGroup.getArrivalDate() : getRandomDate())
                    .status(type.equals(OrderType.ALL) ? status : orderDetailStatus)
                    .build();

            orderDetailRepository.save(orderDetail);
            orderHistoryDetails.add(orderDetail);
        }


    }


    private LocalDateTime getRandomDate() {
        return LocalDateTime.of(2019, getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    private int getRandomNumber() {
        return random.nextInt(11) + 1;
    }
}
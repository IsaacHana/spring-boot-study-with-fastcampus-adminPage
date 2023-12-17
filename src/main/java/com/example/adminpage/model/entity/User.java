package com.example.adminpage.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Table(name = "user") 클래스 이름과 테이블 이름이 동일하면 자동 변환 아닐 경우 이러한 방식으로 명시적 변환 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(name = "account") 명시적 맵핑
    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // 1 : N
    // LAZY = 지연 로딩
    // oneToMany , manyToMany 의 default
    // select * from user u1_0 where u1_0.id=?
    // 필요한 시점에 연관된 데이터를 불러옴

    // EAGER = 즉시 로딩
    // select * from user u1_0
    // left join order_detail odl1_0 on u1_0.id=odl1_0.user_id
    // left join item i1_0 on i1_0.id=odl1_0.item_id
    // where u1_0.id=?
    // 연관된 Table을 한 꺼번에 join 시켜서 데이터를 호출함

    // 만약 연관관계가 더 많은 실제 실무 프로젝트에서는
    // 자원의 낭비나 실제로 호출이 안되는 경우도 있기 때문에
    // LAZY를 사용한다고 한다.

    // oneToOnt , manyToOne 의 default
    // mappedBy 는 OrderDetail 클래스안에 user에 매칭 시킨다는 뜻
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private List<OrderDetail> orderDetailList;
}

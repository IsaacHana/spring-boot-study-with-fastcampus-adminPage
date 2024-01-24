package com.example.adminpage.model.entity;

import com.example.adminpage.model.enumclass.OrderDetailType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"orderGroup", "item"})
@Builder
@Accessors(chain = true)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderDetailType status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate // 등록일
    private LocalDateTime createdAt;

    @CreatedBy // 등록자
    private String createdBy;

    @LastModifiedDate // 수정일
    private LocalDateTime updatedAt;

    @LastModifiedBy // 수정자
    private String updatedBy;

    // OrderDetail N : 1 OrderGroup
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderGroup orderGroup;

    // OrderDetail N : 1 Item
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
}

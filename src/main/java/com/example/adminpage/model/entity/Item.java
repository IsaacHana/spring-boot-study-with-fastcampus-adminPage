package com.example.adminpage.model.entity;

import com.example.adminpage.model.enumclass.ItemStatus;
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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"orderDetails", "partnerCompany"})
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate // 등록일
    private LocalDateTime createdAt;

    @CreatedBy // 등록자
    private String createdBy;

    @LastModifiedDate // 수정일
    private LocalDateTime updatedAt;

    @LastModifiedBy // 수정자
    private String updatedBy;

    // Item N : 1 Partner
    @ManyToOne(fetch = FetchType.LAZY)
    private PartnerCompany partnerCompany;

    // Item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetails;
}

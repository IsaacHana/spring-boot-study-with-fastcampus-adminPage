package com.example.adminpage.model.entity;

import com.example.adminpage.model.enumclass.PartnerStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"items", "category"})
@Builder
@Accessors(chain = true)
public class PartnerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

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

    // Partner 1 : N Item
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partnerCompany")
    private List<Item> items;

    // Partner N : 1 Category
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}

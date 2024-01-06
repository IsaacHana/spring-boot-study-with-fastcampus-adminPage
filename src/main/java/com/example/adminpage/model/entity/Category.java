package com.example.adminpage.model.entity;

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
@ToString(exclude = {"partnerCompanies"})
@Builder
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    @CreatedDate // 등록일
    private LocalDateTime createdAt;

    @CreatedBy // 등록자
    private String createdBy;

    @LastModifiedDate // 수정일
    private LocalDateTime updatedAt;

    @LastModifiedBy // 수정자
    private String updatedBy;

    // Category 1 : N PartnerCompany
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<PartnerCompany> partnerCompanies;
}

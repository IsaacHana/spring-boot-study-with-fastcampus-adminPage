package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.PartnerCompany;
import com.example.adminpage.model.enumclass.PartnerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerCompanyRepositoryTest extends AdminPageApplicationTests {
    @Autowired
    private PartnerCompanyRepository partnerCompanyRepository;

    @Test
    public void create() {
        String name = "Partner01";
        PartnerStatus status = PartnerStatus.REGISTERED;
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        PartnerCompany partnerCompany = new PartnerCompany();
        partnerCompany.setName(name);
        partnerCompany.setStatus(status);
        partnerCompany.setAddress(address);
        partnerCompany.setCallCenter(callCenter);
        partnerCompany.setPartnerNumber(partnerNumber);
        partnerCompany.setBusinessNumber(businessNumber);
        partnerCompany.setCeoName(ceoName);
        partnerCompany.setRegisteredAt(registeredAt);
        partnerCompany.setCreatedAt(createdAt);
        partnerCompany.setCreatedBy(createdBy);
//        partnerCompany.setCategoryId(categoryId);

        PartnerCompany newPartnerCompany = partnerCompanyRepository.save(partnerCompany);
        Assertions.assertNotNull(newPartnerCompany);
        Assertions.assertEquals(newPartnerCompany.getName(), name);
    }

    @Test
    public void read() {
        Optional<PartnerCompany> partnerCompany = partnerCompanyRepository.findById(1L);
        Assertions.assertNotNull(partnerCompany);
    }
}

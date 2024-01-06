package com.example.adminpage.repository;

import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.entity.PartnerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long> {
    List<PartnerCompany> findByCategory(Category category);
}

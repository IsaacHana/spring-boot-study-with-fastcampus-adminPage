package com.example.adminpage.repository;

import com.example.adminpage.model.entity.PartnerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long>{
}

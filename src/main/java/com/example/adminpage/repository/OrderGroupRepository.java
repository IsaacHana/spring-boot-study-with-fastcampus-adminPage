package com.example.adminpage.repository;

import com.example.adminpage.model.entity.OrderGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    @Query("SELECT og from OrderGroup og JOIN FETCH og.user u where u.id = ?1")
    Page<OrderGroup> findWithUserId(Long id, Pageable pageable);
}

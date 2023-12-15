package com.example.adminpage.repository;

import com.example.adminpage.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository 없어도 동작 하는 지 실험
public interface ItemRepository extends JpaRepository<Item, Long> {
}

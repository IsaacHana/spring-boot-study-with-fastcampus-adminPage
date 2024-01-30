package com.example.adminpage.repository;

import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.enumclass.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByType(CategoryType type);
}

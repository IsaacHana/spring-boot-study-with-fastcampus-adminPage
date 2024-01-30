package com.example.adminpage.repository;

import com.example.adminpage.AdminPageApplicationTests;
import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.enumclass.CategoryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends AdminPageApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        CategoryType type = CategoryType.COMPUTER;
        String title = type.getName();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();

        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read() {
        // select * from category where id = '1L'
        // Optional<Category> optionalCategory = categoryRepository.findById(1L);

        // select * from category where type = 'COMPUTER'
        CategoryType type = CategoryType.COMPUTER;
        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        optionalCategory.ifPresent(category -> {
            Assertions.assertEquals(category.getType(), type);

            System.out.println(category.getId());
            System.out.println(category.getType());
            System.out.println(category.getTitle());
        });
    }

    @Test
    public void update() {
        Optional<Category> optionalCategory = categoryRepository.findById(1L);

        optionalCategory.ifPresent(category -> {
            category.setType(CategoryType.COMPUTER);
            category.setUpdatedAt(LocalDateTime.now());
            category.setUpdatedBy("update method()");

            categoryRepository.save(category);
        });
    }

    @Test
    public void delete() {
        Optional<Category> optionalCategory = categoryRepository.findById(1L);

        Assertions.assertTrue(optionalCategory.isPresent());

        optionalCategory.ifPresent(category -> {
            categoryRepository.delete(category);
        });

        Optional<Category> deleteCategory = categoryRepository.findById(1L);

        Assertions.assertFalse(deleteCategory.isPresent());
    }
}

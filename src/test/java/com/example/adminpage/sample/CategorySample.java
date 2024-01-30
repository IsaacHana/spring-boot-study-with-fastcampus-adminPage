package com.example.adminpage.sample;

import com.example.adminpage.component.LoginUserAuditorAware;
import com.example.adminpage.config.JpaConfig;
import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.enumclass.CategoryType;
import com.example.adminpage.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest                                                                   // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("CategorySample 생성")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CategorySample {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createSample() {
        CategoryType[] category = CategoryType.values();

        for (int i = 0; i < category.length; i++) {
            CategoryType c = category[i];
            String t = c.getName();
            Category create = Category.builder().type(c).title(t).build();
            categoryRepository.save(create);
        }
    }
}


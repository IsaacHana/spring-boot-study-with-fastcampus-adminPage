package com.example.adminpage.sample;

import com.example.adminpage.component.LoginUserAuditorAware;
import com.example.adminpage.config.JpaConfig;
import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.entity.Item;
import com.example.adminpage.model.entity.PartnerCompany;
import com.example.adminpage.model.enumclass.CategoryType;
import com.example.adminpage.model.enumclass.ItemStatus;
import com.example.adminpage.repository.CategoryRepository;
import com.example.adminpage.repository.ItemRepository;
import com.example.adminpage.repository.PartnerCompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("ItemSample 생성")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ItemSample {

    private Random random = new Random();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerCompanyRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createAll() {
        createHomeAppliance();
        createClothing();
        createInterior();
        createFood();
        createSports();
        createBeauty();
    }


    @Test
    public void createHomeAppliance() {
        CategoryType type = CategoryType.COMPUTER;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 가전제품" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(9999) * 100 + 100))
                        .content(p.getName() + "의 가전제품" + i + "호" + "의 가전제품 입니다. 2023년도 신제품 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();

                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createClothing() {

        CategoryType type = CategoryType.CLOTHES;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 의류제품" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(999) * 100 + 100))
                        .content(p.getName() + "의 의류제품" + i + "호" + "의 겨울 상품 입니다. 2023년도 신제품 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createInterior() {

        CategoryType type = CategoryType.HOME_DECORATION;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 가구" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(999) * 100 + 100))
                        .content(p.getName() + "의 가구" + i + "호" + "의 원룸에 들어가는. 2023년도 신제품 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createFood() {

        CategoryType type = CategoryType.FOOD;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 식당" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(99) * 100 + 100))
                        .content(p.getName() + "의 식당" + i + "호" + "의 음식 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createSports() {

        CategoryType type = CategoryType.SPORTS;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 겨울 스포츠" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(999) * 100 + 100))
                        .content(p.getName() + "의 겨울 스포츠" + i + "호" + "의 스키 상품 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createBeauty() {

        CategoryType type = CategoryType.COSMETICS;
        Category category = categoryRepository.findByType(type).get();
        List<PartnerCompany> partnerList = partnerRepository.findByCategory(category);

        for (PartnerCompany p : partnerList) {

            for (int i = 1; i < 6; i++) {
                int div = (random.nextInt(10) + 1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partnerCompany(p)
                        .status(status)
                        .name(category.getTitle() + i + "호")
                        .title(p.getName() + "의 OO 화장품" + i + "호")
                        .price(BigDecimal.valueOf((long) random.nextInt(999) * 100 + 100))
                        .content(p.getName() + "의 OO 화장품" + i + "호" + "의 상품 입니다")
                        .brandName(p.getName())
                        .thumbnail(type.getFilePrefix() + i)
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null)
                        .build();
                itemRepository.save(item);
            }
        }
    }

    private LocalDateTime getRandomDate() {
        return LocalDateTime.of(2023, getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    private int getRandomNumber() {
        return random.nextInt(11) + 1;
    }
}

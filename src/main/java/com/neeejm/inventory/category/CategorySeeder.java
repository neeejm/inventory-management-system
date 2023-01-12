package com.neeejm.inventory.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("categorySeeder")
public class CategorySeeder {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        loadCategories();
    }

    private void loadCategories() {
        if (categoryRepository.findAll().isEmpty()) {
            CategoryEntity category = categoryRepository.save(
                CategoryEntity.builder()
                        .name("default category")
                        .type(CategoryEntity.Type.PARENT_CATEGORY.toString())
                        .build()
            );
            categoryRepository.save(
                CategoryEntity.builder()
                        .name("default subcategory")
                        .type(CategoryEntity.Type.SUBCATEGORY.toString())
                        .parentCategory(category)
                        .build()
            );
            log.info("[SEED] Categories seeding done");
       } else {
            log.info("[SEED] Categories seeding not required");
       }
    }
}

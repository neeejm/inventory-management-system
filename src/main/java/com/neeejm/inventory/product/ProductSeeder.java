package com.neeejm.inventory.product;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.category.CategoryEntity;
import com.neeejm.inventory.category.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("productSedder")
public class ProductSeeder {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @EventListener
    @DependsOn({"categorySeeder"})
    public void seed(ContextRefreshedEvent event) {
        loadProducts();
    }

    private void loadProducts() {
        if (productRepository.findAll().isEmpty()) {
            CategoryEntity subcategory = categoryRepository.findByName("default subcategory").orElseThrow();

            productRepository.save(
                ProductEntity.builder()
                        .name("product 1")
                        .reference("p1")
                        .costPrice(new BigDecimal(10))
                        .unitPrice(new BigDecimal(15))
                        .subcategory(subcategory)
                        .build()
            );
            productRepository.save(
                ProductEntity.builder()
                        .name("product 2")
                        .reference("p2")
                        .costPrice(new BigDecimal(100))
                        .unitPrice(new BigDecimal(150))
                        .subcategory(subcategory)
                        .build()
            );
            log.info("[SEED] Products seeding done.");
        } else {
            log.info("[SEED] Products seeding not required.");
        }
    }
}

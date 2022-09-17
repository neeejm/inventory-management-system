package com.neeejm.inventory.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {
    
    @Bean
    public CategoryListener categoryListener() {
        return new CategoryListener();
    }
}

package com.neeejm.inventory.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    
    @Bean
    public CustomerListener customerListener() {
        return new CustomerListener();
    }
}

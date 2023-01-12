package com.neeejm.inventory.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    
    @Bean
    public OrderListener orderListener() {
        return new OrderListener();
    }
}

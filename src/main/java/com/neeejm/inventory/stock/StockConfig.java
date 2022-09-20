package com.neeejm.inventory.stock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig {
    
    @Bean
    public StockListener stockListener() {
        return new StockListener();
    }
}

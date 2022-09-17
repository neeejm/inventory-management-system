package com.neeejm.inventory.role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {
    
    @Bean
    public RoleListener roleListener() {
        return new RoleListener();
    }
}

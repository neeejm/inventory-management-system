package com.neeejm.inventory.common.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neeejm.inventory.role.RoleListener;

@Configuration
public class AppConfig {
    
    @Bean
    public RoleListener roleListener() {
        return new RoleListener();
    }
}

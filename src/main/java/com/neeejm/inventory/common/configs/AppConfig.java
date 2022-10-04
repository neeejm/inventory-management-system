package com.neeejm.inventory.common.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neeejm.inventory.category.CategoryInterceptor;
import com.neeejm.inventory.common.utils.UrlsUtil;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {

    //     registry.addInterceptor(new CategoryInterceptor())
    //             .addPathPatterns("/api/v1/categories");
    // }
}

package com.neeejm.inventory.common.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Urls {
    
    public static String getBaseURL() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
    }

    public static String getRequestUrl() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUriString();
    }
}
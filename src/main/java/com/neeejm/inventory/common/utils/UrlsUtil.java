package com.neeejm.inventory.common.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UrlsUtil {
    
    private UrlsUtil() {
        throw new IllegalStateException("Utility Class");
    }

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
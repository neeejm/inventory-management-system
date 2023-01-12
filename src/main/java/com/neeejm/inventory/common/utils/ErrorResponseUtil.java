package com.neeejm.inventory.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import com.neeejm.inventory.common.errors.ApiError;

public class ErrorResponseUtil {
    
    private ErrorResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<ApiError<String>> handleErrorResponse(
            HttpStatus status,
            Exception exception,
            boolean includeStackTrace) {

        if (Boolean.TRUE.equals(includeStackTrace)) {
            return ResponseEntity.status(status).body(
                    new ApiError<String>(
                            status,
                            getErrorMessages(exception),
                            getStackTrace(exception),
                            UrlsUtil.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError<String>(
                        status,
                        getErrorMessages(exception),
                        UrlsUtil.getRequestUrl()));
    }

    public static <T> ResponseEntity<ApiError<T>> handleErrorResponse(
            HttpStatus status,
            Set<T> msgs,
            Exception exception,
            boolean includeStackTrace) {

        if (Boolean.TRUE.equals(includeStackTrace)) {
            return ResponseEntity.status(status).body(
                    new ApiError<>(
                            status,
                            msgs,
                            getStackTrace(exception),
                            UrlsUtil.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError<>(
                        status,
                        msgs,
                        UrlsUtil.getRequestUrl()));
    }

    private static String getStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private static Set<String> getErrorMessages(Exception exception) {
        if (exception.getMessage().contains(",")) {
            return Set.of(StringUtils.split(exception.getMessage(), ","));
        }
        return Set.of(exception.getMessage());
    }
}

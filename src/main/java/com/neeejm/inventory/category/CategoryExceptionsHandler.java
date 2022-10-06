package com.neeejm.inventory.category;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.neeejm.inventory.category.exceptions.CategoryExistsException;
import com.neeejm.inventory.category.exceptions.InvalidCategoryException;
import com.neeejm.inventory.category.exceptions.ParentCategoryNotFoundException;
import com.neeejm.inventory.common.errors.ApiError;
import com.neeejm.inventory.common.errors.ValidationError;
import com.neeejm.inventory.common.utils.ErrorResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CategoryExceptionsHandler {

        @Value("#{new Boolean('${app.api-error.include-stacktrace:false}')}")
        private Boolean includeStackTrace;

        @ExceptionHandler({
                        InvalidCategoryException.class
        })
        private ResponseEntity<ApiError<ValidationError>> handleException(
                        InvalidCategoryException exception) throws Exception {

                log.error("[CATEGORY_VALIDATION]", exception);

                exception.getMessages().forEach(v -> log.info("Validator {} / {}", v.getField(), v.getMessage()));
                return ErrorResponseUtil.handleErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                exception.getMessages(),
                                exception,
                                includeStackTrace);
        }

        @ExceptionHandler({
                        CategoryExistsException.class
        })
        private ResponseEntity<ApiError<String>> handleException(
                        CategoryExistsException exception) throws Exception {

                log.error("[CATEGORY_EXIST]", exception);

                return ErrorResponseUtil.handleErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                exception,
                                includeStackTrace);
        }

        @ExceptionHandler({
                        ParentCategoryNotFoundException.class
        })
        private ResponseEntity<ApiError<String>> handleException(
                        ParentCategoryNotFoundException exception) throws Exception {

                log.error("[PARENT_CATEGORY_NOT_FOUND]", exception);

                return ErrorResponseUtil.handleErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                exception,
                                includeStackTrace);
        }

        @ExceptionHandler({
                        MethodArgumentNotValidException.class
        })
        private ResponseEntity<ApiError<String>> handleException(
                        Exception exception) throws Exception {

                log.error("[CATEGORY_VALIDATION]", exception);

                log.info("Validator {}", "mamamia");
                return ErrorResponseUtil.handleErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                exception,
                                includeStackTrace);
        }
}

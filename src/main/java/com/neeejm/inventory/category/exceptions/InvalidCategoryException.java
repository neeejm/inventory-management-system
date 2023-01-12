package com.neeejm.inventory.category.exceptions;

import java.util.Set;

import com.neeejm.inventory.common.errors.ValidationError;

import lombok.Getter;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException(Set<ValidationError> messages) {
        this.messages = messages;
    }

    @Getter
    private final Set<ValidationError> messages;
}

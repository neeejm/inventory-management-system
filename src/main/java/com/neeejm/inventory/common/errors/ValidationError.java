package com.neeejm.inventory.common.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationError {
    private String field;
    private String message;
}

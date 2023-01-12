package com.neeejm.inventory.category.exceptions;

public class ParentCategoryNotFoundException extends RuntimeException {

    public ParentCategoryNotFoundException(String message) {
        super(message);
    }
    
}

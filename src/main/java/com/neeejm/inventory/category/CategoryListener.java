package com.neeejm.inventory.category;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(CategoryEntity.class)
public class CategoryListener {
    
    private final String EXISTS_MSG = "Category '%s' already exists.";

    @Autowired
    private CategoryRepository categoryRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(CategoryEntity categoryToCreate) {
        log.info("[CATEGORY_CHECK]");

        Optional<CategoryEntity> category = categoryRepository.findByName(categoryToCreate.getName());
        if (category.isPresent()) {
            log.error("[CATEGORY_EXISTS] Role: {}", categoryToCreate.getName());
            throw new EntityExistsException(EXISTS_MSG.formatted(categoryToCreate.getName()));
        }
    }
}

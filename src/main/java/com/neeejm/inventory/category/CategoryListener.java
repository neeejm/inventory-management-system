package com.neeejm.inventory.category;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeejm.inventory.category.exceptions.CategoryExistsException;
import com.neeejm.inventory.category.exceptions.InvalidCategoryException;
import com.neeejm.inventory.category.exceptions.ParentCategoryNotFoundException;
import com.neeejm.inventory.common.errors.ValidationError;
import com.neeejm.inventory.common.utils.JsonSchemaUtil;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(CategoryEntity.class)
public class CategoryListener {

    private final String EXISTS_MSG = "Category '%s' already exists.";
    private final String PARENT_NOT_FOUND_MSG = "Parent category not found.";

    @Autowired
    private CategoryRepository categoryRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void check(CategoryEntity categoryToCreate) {
        log.info("[CATEGORY_CHECK] {}", categoryToCreate);

        // validateJson(categoryToCreate);

        throwIfAlreadyExists(categoryToCreate);

        throwIfNoParentCategoryFound(categoryToCreate);
    }

    private void validateJson(CategoryEntity categoryToCreate) {
        Set<ValidationMessage> msgs = JsonSchemaUtil.validate(
                categoryToCreate,
                CategoryEntity.class.getResourceAsStream("category.schema.json")
        );
        if (!msgs.isEmpty()) {
            msgs.forEach(msg ->
                log.info("[CATEGORY_JSON_VALIDATION] {}", msg.getMessage())
            );
            throw new InvalidCategoryException(msgs.stream().map(msg ->
                new ValidationError(
                    msg.getPath().substring(2),
                    msg.getMessage()
                )
            ).collect(Collectors.toSet()));
        }
    }

    private void throwIfNoParentCategoryFound(CategoryEntity categoryToCreate) {
        if (categoryToCreate.getType().equals(
                CategoryEntity.Type.SUBCATEGORY.toString()) &&
                categoryToCreate.getParentCategory() == null) {
            log.info(PARENT_NOT_FOUND_MSG);
            throw new ParentCategoryNotFoundException(PARENT_NOT_FOUND_MSG);
        }
    }

    private void throwIfAlreadyExists(CategoryEntity categoryToCreate) {
        Optional<CategoryEntity> category = categoryRepository.findByName(categoryToCreate.getName());
        if (category.isPresent()) {
            log.error("[CATEGORY_EXISTS] Role: {}", categoryToCreate.getName());
            throw new CategoryExistsException(EXISTS_MSG.formatted(categoryToCreate.getName()));
        }
    }
}

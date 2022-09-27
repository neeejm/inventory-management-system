package com.neeejm.inventory.category;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void throwIfExists(CategoryEntity categoryToCreate) throws Exception {
        log.info("[CATEGORY_CHECK] {}", categoryToCreate);

        Set<ValidationMessage> msgs = validate(categoryToCreate);
        if (!msgs.isEmpty()) {
            msgs.forEach(msg ->
                log.info("[VALIDATION] {}", msg)
            );
            throw new Exception("noooooooooooooooooooooo");
        }

        Optional<CategoryEntity> category = categoryRepository.findByName(categoryToCreate.getName());
        if (category.isPresent()) {
            log.error("[CATEGORY_EXISTS] Role: {}", categoryToCreate.getName());
            throw new EntityExistsException(EXISTS_MSG.formatted(categoryToCreate.getName()));
        }

        if (categoryToCreate.getType().equalsIgnoreCase(
                CategoryEntity.CategoryType.SUBCATEGORY.toString()) &&
                categoryToCreate.getParentCategory() == null) {
            log.info(PARENT_NOT_FOUND_MSG);
            throw new EntityNotFoundException(PARENT_NOT_FOUND_MSG);
        }
    }

    private Set<ValidationMessage> validate(CategoryEntity categoryToCreate) {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        JsonSchema jsonSchema = factory.getSchema(
                CategoryEntity.class.getResourceAsStream("category.schema.json")
        );
        JsonNode data = mapper.valueToTree(categoryToCreate);
        return jsonSchema.validate(data);
    }
}

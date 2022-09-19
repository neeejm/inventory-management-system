package com.neeejm.inventory.product;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(ProductEntity.class)
public class ProductListener {
    
    private final String EXISTS_MSG = "Product '%s' already exists.";
    private final String SUBCATEGORY_NOT_FOUND_MSG = "Subcategory not found.";

    @Autowired
    private ProductRepository productRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(ProductEntity productToCreate) {
        log.info("[PRODUCT_CHECK] {}", productToCreate);

        Optional<ProductEntity> product = productRepository.findByReference(productToCreate.getReference());
        if (product.isPresent()) {
            log.error("[PRODUCT_EXISTS] Product: {}", productToCreate.getName());
            throw new EntityExistsException(EXISTS_MSG.formatted(productToCreate.getReference()));
        }

        if (productToCreate.getSubcategory() == null) {
                log.info("eeeeeeeeeeeeeeeeeeeeeeeeh");
                throw new EntityNotFoundException(SUBCATEGORY_NOT_FOUND_MSG);
        }
    }
}

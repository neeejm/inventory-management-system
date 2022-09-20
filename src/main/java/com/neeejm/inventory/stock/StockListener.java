package com.neeejm.inventory.stock;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(StockEntity.class)
public class StockListener {
    
    private final String EXISTS_MSG = "Stock '%s' already exists.";

    @Autowired
    private StockRepository stockRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(StockEntity stockToCreate) {
        log.info("[STOCK_CHECK] {}", stockToCreate);

        Optional<StockEntity> stock = stockRepository.findByName(stockToCreate.getName());
        if (stock.isPresent()) {
            log.error("[CATEGORY_EXISTS] Role: {}", stockToCreate.getName());
            throw new EntityExistsException(EXISTS_MSG.formatted(stockToCreate.getName()));
        }
    }
}

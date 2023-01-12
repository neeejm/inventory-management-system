package com.neeejm.inventory.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.common.entities.AddressEntity;
import com.neeejm.inventory.stock.entities.StockEntity;
import com.neeejm.inventory.stock.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("stockSeeder")
public class StockSeeder {
    
    @Autowired
    private StockRepository stockRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        loadStocks();
    }

    private void loadStocks() {
        if (stockRepository.findAll().isEmpty()) {
            stockRepository.save(
                StockEntity.builder()
                        .name("stock 1")
                        .address(
                            AddressEntity.builder()
                                    .country("country 1")
                                    .city("city 1")
                                    .street("street 1")
                                    .zipCode("10000")
                                    .build()
                        )
                        .build()
            );
            stockRepository.save(
                StockEntity.builder()
                        .name("stock 2")
                        .address(
                            AddressEntity.builder()
                                    .country("country 2")
                                    .city("city 2")
                                    .street("street 2")
                                    .zipCode("20000")
                                    .build()
                        )
                        .build()
            );
            log.info("[SEED] Stock seeding done");
        } else {
            log.info("[SEED] Stock seeding not requied");
        }
    }
}

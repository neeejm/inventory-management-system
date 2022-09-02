package com.neeejm.inventory.stockProduct;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface StockProductRepository extends JpaRepository<StockProductEntity, StockProductId> {
    
    @RestResource(path = "product-by-stock")
    Page<StockProductEntity> findByStockId(@Param("stockId") UUID stockId, Pageable pageable);
}

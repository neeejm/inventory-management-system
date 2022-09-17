package com.neeejm.inventory.stockproduct;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    exported = false
)
public interface StockProductRepository extends JpaRepository<StockProductEntity, StockProductId> {
    
    @RestResource(path = "product-by-stock")
    Page<StockProductEntity> findByStockId(@Param("stockId") UUID stockId, Pageable pageable);
}

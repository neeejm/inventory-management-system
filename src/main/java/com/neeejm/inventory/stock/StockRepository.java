package com.neeejm.inventory.stock;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {

    // @RestResource(path = "product-by-stock")
    // @Query(value = "SELECT p, sp.quantity from product p, stock_product sp " +
    //             "WHERE sp.stock_id = :stockId", nativeQuery = true)
    // Page<StockProduct> findProductsByStockId(@Param("stockId") UUID stockId, Pageable pageable);


    @RestResource(path = "product-by-stock")
    @Query(value = "SELECT sp from stock_product sp " +
                "WHERE sp.stock_id = :stockId", nativeQuery = true)
    Page<StockEntity> finedByStockProductIdstockId(@Param("stockId") UUID stockId, Pageable pageable);
}

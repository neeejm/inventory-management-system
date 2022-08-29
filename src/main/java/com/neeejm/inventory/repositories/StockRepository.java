package com.neeejm.inventory.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.entities.Product;
import com.neeejm.inventory.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

    @RestResource(path = "product-by-stock")
    @Query(value = "SELECT p, sp.quantity from Product p, StockProduct sp " +
                "WHERE sp.id.stockId = :stockId")
    Page<Product> findProductsByStockId(@Param("stockId") UUID stockId, Pageable pageable);
}

package com.neeejm.inventory.stock.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.neeejm.inventory.stock.entities.StockEntity;

@RepositoryRestResource(
    path = "stocks",
    collectionResourceRel = "stocks",
    itemResourceRel = "stock"
)
public interface StockRepository extends JpaRepository<StockEntity, UUID> {

    @RestResource(exported = false)
    Optional<StockEntity> findByName(String name);

    @RestResource(path = "product-by-stock")
    @Query(value = "SELECT sp from stock_product sp " +
                "WHERE sp.stock_id = :stockId", nativeQuery = true)
    Page<StockEntity> finedByStockProductIdstockId(@Param("stockId") UUID stockId, Pageable pageable);
}

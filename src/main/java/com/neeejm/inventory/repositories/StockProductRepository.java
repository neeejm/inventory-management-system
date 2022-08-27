package com.neeejm.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.entities.StockProduct;
import com.neeejm.inventory.entities.StockProductId;

@Repository
public interface StockProductRepository extends JpaRepository<StockProduct, StockProductId> {
    
}

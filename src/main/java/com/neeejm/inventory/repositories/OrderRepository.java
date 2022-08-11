package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByTypeOrderByCreatedAtDesc(Order.Type type);
}

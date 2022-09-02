package com.neeejm.inventory.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.order.entities.OrderEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByTypeOrderByCreatedAtDesc(OrderEntity.Type type);
}

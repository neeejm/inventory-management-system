package com.neeejm.inventory.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.order.entities.OrderEntity;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(
    path = "orders",
    collectionResourceRel = "orders",
    itemResourceRel = "order"
)
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByTypeOrderByCreatedAtDesc(OrderEntity.Type type);
}

package com.neeejm.inventory.order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.neeejm.inventory.order.entities.OrderEntity;

@RepositoryRestResource(
    path = "orders",
    collectionResourceRel = "orders",
    itemResourceRel = "order"
)
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @RestResource(exported = false)
    Optional<OrderEntity> findByReference(String reference);

    List<OrderEntity> findByTypeOrderByCreatedAtDesc(OrderEntity.Type type);
}

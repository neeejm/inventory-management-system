package com.neeejm.inventory.order;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.neeejm.inventory.order.entities.OrderEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(OrderEntity.class)
public class OrderListener {

    private final String EXISTS_MSG = "Order '%s' already exists.";
    
    @Autowired
    private OrderRepository orderRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(OrderEntity orderToCreate) {
        log.info("[ORDER_CHECK]");

        Optional<OrderEntity> order = orderRepository.findByReference(orderToCreate.getReference());
        if (order.isPresent()) {
            log.error("[ORDER_EXISTS] Order: {}", orderToCreate.getReference());
            throw new EntityExistsException(EXISTS_MSG.formatted(orderToCreate.getReference()));
        }
    }
}

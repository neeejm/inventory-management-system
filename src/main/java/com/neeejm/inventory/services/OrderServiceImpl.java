package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Order;
import com.neeejm.inventory.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderRepository, Order>
        implements OrderService {

    @Autowired
    protected OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}

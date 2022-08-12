package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Stock;
import com.neeejm.inventory.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends CrudServiceImpl<StockRepository, Stock>
        implements StockService {

    @Autowired
    protected StockServiceImpl(StockRepository repository) {
        super(repository);
    }
}

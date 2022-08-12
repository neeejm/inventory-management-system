package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Product;
import com.neeejm.inventory.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CrudServiceImpl<ProductRepository, Product>
        implements ProductService {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }
}

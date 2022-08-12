package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Product;
import com.neeejm.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private static final String NOT_FOUND_EXCEPTION_MSG = "Product not found";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        if(!productRepository.existsById(id)) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return product.get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}

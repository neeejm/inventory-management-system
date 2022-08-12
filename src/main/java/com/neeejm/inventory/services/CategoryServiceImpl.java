package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Category;
import com.neeejm.inventory.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryRepository, Category>
        implements CategoryService {

    @Autowired
    protected CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}

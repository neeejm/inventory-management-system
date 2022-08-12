package com.neeejm.inventory.services;

import java.util.List;
import java.util.UUID;

public interface CrudService<T> {
    T add(T t);
    T update(T t);
    void deleteById(UUID id);
    T findById(UUID id);
    List<T> findAll();
}

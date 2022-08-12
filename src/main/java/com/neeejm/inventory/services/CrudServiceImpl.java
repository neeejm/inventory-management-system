package com.neeejm.inventory.services;

import com.neeejm.inventory.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CrudServiceImpl<S extends JpaRepository<T, UUID>, T extends BaseEntity>
        implements CrudService<T> {
    private final S repository;
    private static final String NOT_FOUND_EXCEPTION_MSG = "Entity not found";

    protected CrudServiceImpl(S repository) {
        this.repository = repository;
    }

    @Override
    public T add(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        if (!repository.existsById(t.getId())) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return repository.save(t);
    }

    @Override
    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        repository.deleteById(id);
    }

    @Override
    public T findById(UUID id) {
        Optional<T> t = repository.findById(id);
        if (t.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return t.get();
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}

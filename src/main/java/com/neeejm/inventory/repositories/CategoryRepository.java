package com.neeejm.inventory.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @RestResource(path = "type")
    List<Category> findByTypeIgnoreCase(@Param("value") String type, Pageable pageable);

    @RestResource(path = "name-containing")
    List<Category> findByNameIgnoreCaseContaining(@Param("value") String name, Pageable pageable);
}

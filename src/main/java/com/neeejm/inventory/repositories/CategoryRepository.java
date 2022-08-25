package com.neeejm.inventory.repositories;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @RestResource(path = "type")
    Set<Category> findByTypeIgnoreCase(@Param("type") String type);

    @RestResource(path = "name")
    Set<Category> findByNameContaining(@Param("name") String name);

    @RestResource(path = "subcategories")
    Set<Category> findByParentCategoryName(@Param("parent") String name);
}

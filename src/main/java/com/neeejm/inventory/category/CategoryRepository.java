package com.neeejm.inventory.category;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    @RestResource(path = "type")
    List<CategoryEntity> findByTypeIgnoreCase(@Param("value") String type, Pageable pageable);

    @RestResource(path = "name-containing")
    List<CategoryEntity> findByNameIgnoreCaseContaining(@Param("value") String name, Pageable pageable);
}

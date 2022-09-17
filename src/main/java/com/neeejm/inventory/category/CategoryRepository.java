package com.neeejm.inventory.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    path = "categories",
    collectionResourceRel = "categories",
    itemResourceRel = "category"
)
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    Optional<CategoryEntity> findByName(String name);

    @RestResource(path = "type")
    List<CategoryEntity> findByTypeIgnoreCase(@Param("type") String type, Pageable pageable);

    @RestResource(path = "name-containing")
    List<CategoryEntity> findByNameIgnoreCaseContaining(@Param("name") String name, Pageable pageable);
}

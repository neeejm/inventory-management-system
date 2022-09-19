package com.neeejm.inventory.product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    path = "products",
    collectionResourceRel = "products",
    itemResourceRel = "product"
)
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @RestResource(exported = false)
    Optional<ProductEntity> findByReference(String ref);
}

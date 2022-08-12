package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Category;
import com.neeejm.inventory.models.Product;
import com.neeejm.inventory.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private final String PRODUCT_NOT_FOUND_MSG = "Product not found";
    @Mock
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;
    private ProductService underTest;
    private Product product;

    @BeforeEach
    void setup() {
        underTest = new ProductServiceImpl(productRepository);

        product = Product.builder()
                .name("product name")
                .costPrice(new BigDecimal("100.0"))
                .unitPrice(new BigDecimal("100.0"))
                .reference("pr-01")
                .subCategory(Category.builder().name("test category").build())
                .build();
    }

    @Test
    @DisplayName("Should save Product")
    void shouldAdd() {
        // When
        underTest.add(product);

        // Then
        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct= productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    @DisplayName("Should update Product")
    void shouldUpdate() {
        // Given
        product.setName("product name 2");

        given(productRepository.existsById(product.getId())).willReturn(true);

        // When
        underTest.update(product);

        // Then
        then(productRepository).should().save(productArgumentCaptor.capture());
        Product capturedProduct= productArgumentCaptor.getValue();
        assertThat(capturedProduct).isNotNull().isEqualTo(product);
    }

    @Test
    @DisplayName("Should not update Product")
    void shouldNotUpdate() {
        // Given
        given(productRepository.existsById(product.getId())).willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.update(product))
                .hasMessageContaining(PRODUCT_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }
    @Test
    @DisplayName("Should delete product with given id")
    void shouldDeleteById() {
        // Given
        UUID id = UUID.randomUUID();

        given(productRepository.existsById(id)).willReturn(true);

        // When
        underTest.deleteById(id);

        // Then
        then(productRepository).should().deleteById(id);
    }

    @Test
    @DisplayName("Should not delete product")
    void shouldNotDeleteById() {
        // Given
        UUID id = UUID.randomUUID();

        given(productRepository.existsById(id)).willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.deleteById(id))
                .hasMessageContaining(PRODUCT_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Should find address by id")
    void shouldFindById() {
        // Given
        UUID id = UUID.randomUUID();

        given(productRepository.findById(id)).willReturn(Optional.of(product));

        // When
        underTest.findById(id);

        // Then
        then(productRepository).should().findById(id);
    }

    @Test
    @DisplayName("Should not find product")
    void shouldNotFindById() {
        // Given
        UUID id = UUID.randomUUID();

        given(productRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.findById(id))
                .hasMessageContaining(PRODUCT_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Should find all addresses")
    void shouldFindAll() {
        // When
        underTest.findAll();

        // Then
        then(productRepository).should().findAll();
    }

}
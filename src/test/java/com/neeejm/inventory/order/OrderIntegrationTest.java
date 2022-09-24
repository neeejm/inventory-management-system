package com.neeejm.inventory.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.neeejm.inventory.category.CategoryEntity;
import com.neeejm.inventory.category.CategoryRepository;
import com.neeejm.inventory.common.entities.AddressEntity;
import com.neeejm.inventory.customer.entities.CompanyEntity;
import com.neeejm.inventory.customer.entities.CustomerEntity;
import com.neeejm.inventory.customer.repositories.CompanyRepository;
import com.neeejm.inventory.order.entities.LineItemEntity;
import com.neeejm.inventory.order.entities.OrderEntity;
import com.neeejm.inventory.product.ProductEntity;
import com.neeejm.inventory.product.ProductRepository;
import com.neeejm.inventory.stock.entities.StockEntity;
import com.neeejm.inventory.stock.repositories.StockRepository;
import com.neeejm.inventory.user.UserEntity;
import com.neeejm.inventory.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {

    private static final String APPLICATION_HAL_JSON = "application/hal+json";
    private static final String APPLICATION_JSON = "application/json";

    private final String ROLE_NOT_FOUND_MESSAGE = "Role with id: '%s' not found";

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    private OrderEntity order;
    private CompanyEntity company;
    private UserEntity user;
    private CategoryEntity subcategory;
    private ProductEntity product;
    private StockEntity stock;
    private LineItemEntity lineItem;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private WebTestClient wClient;

    @BeforeEach
    void setup() {
        user = userRepository.findByEmail("admin@mail.com").get();

        AddressEntity address = AddressEntity.builder()
                                    .country("country")
                                    .city("city")
                                    .street("street")
                                    .zipCode("40000")
                                    .build();

        CategoryEntity category = CategoryEntity.builder()
                                    .name("parent")
                                    .type(CategoryEntity.CategoryType.PARENT_CATEGORY.toString())
                                    .build();
        category = categoryRepository.saveAndFlush(category);

        subcategory = CategoryEntity.builder()
                        .name("sub")
                        .type(CategoryEntity.CategoryType.SUBCATEGORY.toString())
                        .parentCategory(category)
                        .build();
        subcategory = categoryRepository.saveAndFlush(subcategory);

        product = ProductEntity.builder()
                        .name("product")
                        .costPrice(new BigDecimal(15))
                        .unitPrice(new BigDecimal(16))
                        .reference("x123")
                        .subcategory(subcategory)
                        .build();
        product = productRepository.saveAndFlush(product);

        stock = StockEntity.builder()
                    .name("stocki")
                    .address(address)
                    .build();
        stock = stockRepository.saveAndFlush(stock);

        company = CompanyEntity.builder()
                    .email("comptest@mail.com")
                    .primaryPhone("+447716535176")
                    // .addresses(Set.of(address))
                    .name("comp test")
                    .build();
        company = companyRepository.saveAndFlush(company);

        lineItem = LineItemEntity.builder()
                        .quantity(10)
                        .product(product)
                        .stock(stock)
                        .build();

        order = OrderEntity.builder()
                    .reference("x123")
                    .expectedShipmentDate(new Date())
                    .actualShipmentDate(new Date())
                    .type(OrderEntity.Type.SALE.toString())
                    .customer(company)
                    .user(user)
                    .lineItems(Set.of(lineItem))
                    .status(OrderEntity.Status.DRAFT.toString())
                    .build();
    }

    @Test
    @Transient
    void shouldAddOrder() {
        String url = basePath + "/orders";

        // When
        // Then
        wClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(order)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_HAL_JSON);
                // .expectBody(OrderEntity.class).value(o -> {
                //     assertThat(o.getId()).isEqualTo(order.getId());
                //     assertThat(o.getLineItems()).isNotEmpty().hasSize(1)
                //             .containsExactly(lineItem);
                // });
    }
}

package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class OrderRepositoryTest {

    private final OrderRepository underTest;

    @Autowired
    OrderRepositoryTest(OrderRepository underTest) {
        this.underTest = underTest;
    }

    @AfterEach
    void tearDowns() {
        underTest.deleteAll();
    }

    @ParameterizedTest
    @EnumSource(
            value = Order.Type.class,
            names = {"SALE", "PURCHASE"}
    )
    @DisplayName("Check if orders are found by type and ordered by creation date descending")
    void checkFindByTypeOrderByCreatedAtDesc(Order.Type type) throws ParseException {
        // Given
        Order orderOne = Order.builder()
                .createdAt(
                        new SimpleDateFormat("yyyy-MM-dd")
                                .parse("2022-01-01")
                ).reference("or-01")
                .expectedShipmentDate(new Date())
                .actualShipmentDate(new Date())
                .status(Order.Status.ORDERED)
                .type(type)
                .build();
        Order orderTwo = Order.builder()
                .createdAt(
                        new SimpleDateFormat("yyyy-MM-dd")
                                .parse("2021-01-01")
                ).reference("or-02")
                .expectedShipmentDate(new Date())
                .actualShipmentDate(new Date())
                .status(Order.Status.ORDERED)
                .type(type)
                .build();
        underTest.save(orderOne);
        underTest.save(orderTwo);

        // When
        List<Order> expected = underTest.findByTypeOrderByCreatedAtDesc(type);

        // Then
        assertThat(expected)
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(orderOne, orderTwo);
    }
}
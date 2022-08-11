package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository underTest;

    private Order order;

    @BeforeEach
    void setup() throws ParseException {
        order = Order.builder()
                .createdAt(
                        new SimpleDateFormat("yyyy-MM-dd")
                                .parse("2022-01-01")
                ).reference("or-01")
                .expectedShipmentDate(new Date())
                .actualShipmentDate(new Date())
                .status(Order.Status.ORDERED)
                .build();
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
    void checkFindByTypeOrderByCreatedAtDesc(Order.Type type) {
        // Given
        order.setType(type);
        underTest.save(order);

        // When
        List<Order> expected = underTest.findByTypeOrderByCreatedAtDesc(type);

        // Then
        assertThat(expected).containsExactly(order);
    }
}
package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderProduct {
    @EmbeddedId
    private OrderProductId orderProductId;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @NonNull
    @Column(nullable = false)
    private Integer quantity;

    @NonNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
}

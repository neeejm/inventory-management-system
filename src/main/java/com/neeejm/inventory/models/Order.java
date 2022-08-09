package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String reference;

    @NonNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @NonNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "text")
    private String terms;

    private String invoiceUrl;

    public enum Type {
        PURCHASE, SALE
    }

    public enum Status {
        ORDERED, PACKED, ON_HOLD, IN_TRANSIT, SHIPPED
    }
}

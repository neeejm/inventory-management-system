package com.neeejm.inventory.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String reference;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
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

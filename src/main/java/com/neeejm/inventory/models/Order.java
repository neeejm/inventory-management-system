package com.neeejm.inventory.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"order\"")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "text")
    private String terms;

    private String invoiceUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public enum Type {
        PURCHASE, SALE
    }

    public enum Status {
        ORDERED, PACKED, ON_HOLD, IN_TRANSIT, SHIPPED
    }
}

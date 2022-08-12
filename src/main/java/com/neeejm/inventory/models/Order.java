package com.neeejm.inventory.models;

import com.neeejm.inventory.customvalidator.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "\"order\"")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String reference;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @NotBlank(message = "Can't be blank")
    @ValidEnum(enumClass = Type.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotBlank(message = "Can't be blank")
    @ValidEnum(enumClass = Status.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "text")
    private String terms;

    @URL(
            message = "Not a valid document URL",
            protocol = "https",
            regexp = "(\\.pdf)$"
    )
    private String invoiceUrl;

    @NotNull(message = "Can't be null")
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Client client;

    @NotNull(message = "Can't be null")
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private User user;

    public enum Type {
        PURCHASE, SALE
    }

    public enum Status {
        ORDERED, PACKED, ON_HOLD, IN_TRANSIT, SHIPPED
    }
}

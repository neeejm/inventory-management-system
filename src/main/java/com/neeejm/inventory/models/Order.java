package com.neeejm.inventory.models;

import com.neeejm.inventory.customvalidator.ValidEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String reference;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @NotNull(message = "Can't be null")
    @ValidEnum(enumClass = Type.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Can't be null")
    @ValidEnum(enumClass = Status.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "text")
    private String terms;
    
    @OneToMany(
    		fetch = FetchType.EAGER,
    		cascade = CascadeType.ALL
    	)
    @JoinColumn(name = "order_id")
    @Builder.Default
    private Set<LineItem> lineItems = new HashSet<>();

    @URL(
            message = "Not a valid document URL",
            protocol = "https",
            regexp = "(\\.pdf)$"
    )
    private String invoiceUrl;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Client client;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

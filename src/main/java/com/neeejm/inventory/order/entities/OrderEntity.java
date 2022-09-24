package com.neeejm.inventory.order.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.common.util.validators.annotations.ValidEnum;
import com.neeejm.inventory.customer.entities.CompanyEntity;
import com.neeejm.inventory.customer.entities.CustomerEntity;
import com.neeejm.inventory.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false, unique = true)
    private String reference;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedShipmentDate;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualShipmentDate;

    @NotBlank(message = "Can't be blank")
    @ValidEnum(enumClass = Type.class)
    @Column(nullable = false)
    private String type;

    @NotNull(message = "Can't be null")
    @ValidEnum(enumClass = Status.class)
    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "text")
    private String terms;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @Builder.Default
    private Set<LineItemEntity> lineItems = new HashSet<>();

    @URL(message = "Not a valid document URL", protocol = "https", regexp = "(\\.pdf)$")
    @Column(unique = true)
    private String invoiceUrl;

    @NotNull(message = "Can't be null")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CompanyEntity customer;

    @NotNull(message = "Can't be null")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserEntity user;

    public enum Type {
        PURCHASE, SALE
    }

    public enum Status {
        DRAFT, ORDERED, SHIPPED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        OrderEntity order = (OrderEntity) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (reference == null ? 0 : reference.hashCode());
        hash = 31 * hash + (type == null ? 0 : type.hashCode());
        return hash;
    }
}

package com.neeejm.inventory.models;

import com.neeejm.inventory.customvalidator.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Can't be blank")
    @ValidEnum(enumClass = PrivilegeName.class)
    @Column(nullable = false)
    private PrivilegeName name;

    public enum PrivilegeName {
        OP_ALL,

        OP_CREATE_SALES,
        OP_READ_SALES,
        OP_UPDATE_SALES,
        OP_DELETE_SALES,

        OP_CREATE_PURCHASES,
        OP_READ_PURCHASES,
        OP_UPDATE_PURCHASES,
        OP_DELETE_PURCHASES,

        OP_CREATE_PRODUCTS,
        OP_READ_PRODUCTS,
        OP_UPDATE_PRODUCTS,
        OP_DELETE_PRODUCTS,
    }
}

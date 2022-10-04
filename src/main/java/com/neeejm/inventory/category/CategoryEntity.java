package com.neeejm.inventory.category;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.common.utils.validators.annotations.ValidCategory;
import com.neeejm.inventory.common.utils.validators.annotations.ValidEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends BaseEntity {

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @ValidEnum(enumClass = Type.class)
    @Column(nullable = false)
    private String type;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.REMOVE
    )
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    public enum Type {
        PARENT_CATEGORY,
        SUBCATEGORY
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity category = (CategoryEntity) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        return hash;
    }
}

package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import com.neeejm.inventory.customvalidator.ValidEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    @NotBlank(message = "Can't ne blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Can(t be blank")
    @Column(nullable = false)
    @ValidEnum(enumClass = CategoryType.class)
    private String type;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Category parentCategory;

    public enum CategoryType {
        PARENT_CATEGORY,
        SUBCATEGORY
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

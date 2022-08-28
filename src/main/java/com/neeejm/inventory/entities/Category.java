package com.neeejm.inventory.entities;

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
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;

import com.neeejm.inventory.validators.annotations.ValidEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "parentCategory"
    )
    @Builder.Default
    private Set<Category> subcategories = new HashSet<>();

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

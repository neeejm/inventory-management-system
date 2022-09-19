package com.neeejm.inventory.category;

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

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.common.util.validators.annotations.ValidEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @NotBlank(message = "Can't ne blank")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    @ValidEnum(enumClass = CategoryType.class)
    private String type;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "parentCategory"
    )
    @Builder.Default
    private Set<CategoryEntity> subcategories = new HashSet<>();

    public enum CategoryType {
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
        return getClass().hashCode();
    }
}

package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;
}

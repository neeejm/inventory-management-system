package com.neeejm.inventory.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}

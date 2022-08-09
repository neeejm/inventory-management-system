package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Company extends Client{
    @NonNull
    @Column(nullable = false)
    private String name;

//    public Company(@NonNull String name) {
//        this.name = name;
//    }

//    @Builder
//    public Company(UUID id, String displayName, @NonNull String email, @NonNull String primaryPhone, String secondaryPhone, @NonNull String name) {
//        super(id, displayName, email, primaryPhone, secondaryPhone);
//        this.name = name;
//    }
}

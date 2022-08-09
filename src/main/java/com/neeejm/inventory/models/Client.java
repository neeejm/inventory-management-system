package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class Client {
    @Id
    protected UUID id;

    protected String displayName;

    @NonNull
    @Column(nullable = false)
    protected String email;

    @NonNull
    @Column(nullable = false)
    protected String primaryPhone;

    protected String secondaryPhone;

}

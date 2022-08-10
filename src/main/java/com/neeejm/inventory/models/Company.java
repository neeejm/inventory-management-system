package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends Client{
    @Column(nullable = false)
    private String name;
}

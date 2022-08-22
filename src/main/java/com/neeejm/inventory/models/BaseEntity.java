package com.neeejm.inventory.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Long version;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createdAt = new Date();

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date modifiedAt = new Date();
}

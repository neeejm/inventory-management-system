package com.neeejm.inventory.common.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    protected UUID id;

    @Version
    @JsonIgnore
    protected Long version;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @JsonIgnore
    protected Date createdAt = new Date();

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @JsonIgnore
    protected Date modifiedAt = new Date();
}

package com.neeejm.inventory.models;

import java.util.Set;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "role")
@Relation(collectionRelation = "roles")
// @JsonInclude(Include.NON_NULL)
public class RoleModel extends RepresentationModel<RoleModel> {
    private UUID id;
    private String name;

    private Set<PrivilegeModel> privileges;
}

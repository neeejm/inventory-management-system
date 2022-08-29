package com.neeejm.inventory.role;

import java.util.Set;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neeejm.inventory.privilege.PrivilegeDTO;
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
@JsonInclude(Include.NON_NULL)
public class RoleDTO extends RepresentationModel<RoleDTO> {
    private UUID id;
    private String name;

    private Set<PrivilegeDTO> privileges;
}

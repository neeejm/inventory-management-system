package com.neeejm.inventory.privilege.dto;

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
@JsonRootName(value = "privlege")
@Relation(collectionRelation = "privileges")
public class PrivilegeDTO extends RepresentationModel<PrivilegeDTO> {
    private UUID id;
    private String name;
}

package com.neeejm.inventory.dtos.assemblers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeejm.inventory.controllers.RoleContoller;
import com.neeejm.inventory.dtos.PrivilegeModel;
import com.neeejm.inventory.dtos.RoleModel;
import com.neeejm.inventory.entities.Privilege;
import com.neeejm.inventory.entities.Role;

@Component
public class RoleModelAssembler
        extends RepresentationModelAssemblerSupport<Role, RoleModel> {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    public RoleModelAssembler() {
        super(RoleContoller.class, RoleModel.class);
    }

    @Override
    public RoleModel toModel(Role role) {

        RoleModel roleModel = createRessource(role);

        roleModel.add(Link.of(
                getBaseURL() + basePath + "roles/{role_id}")
                .withSelfRel()
                .expand(role.getId()));
        roleModel.add(Link.of(
                getBaseURL() + basePath + "roles/{role_id}")
                .withRel("role")
                .expand(role.getId()));
        roleModel.add(Link.of(
                getBaseURL() + basePath + "roles/{role_id}/privileges")
                .withRel("privileges")
                .expand(role.getId()));

        return roleModel;
    }

    private RoleModel createRessource(Role role) {
        RoleModel roleModel = instantiateModel(role);
        roleModel.setId(role.getId());
        roleModel.setName(role.getName());
        roleModel.setPrivileges(toPrivilegesModel(role.getPrivileges()));
        return roleModel;
    }

    private Set<PrivilegeModel> toPrivilegesModel(Set<Privilege> privileges) {
        if (privileges.isEmpty()) {
            return Collections.emptySet();
        }

        return privileges.stream().map(privilege -> PrivilegeModel.builder()
                .id(privilege.getId())
                .name(privilege.getName())
                .build())
                .collect(Collectors.toSet());
    }

    private String getBaseURL() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
    }
}

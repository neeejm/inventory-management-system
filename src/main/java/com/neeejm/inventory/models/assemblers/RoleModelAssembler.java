package com.neeejm.inventory.models.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeejm.inventory.configs.DataRestConfig;
import com.neeejm.inventory.controllers.RoleContoller;
import com.neeejm.inventory.entities.Privilege;
import com.neeejm.inventory.entities.Role;
import com.neeejm.inventory.models.PrivilegeModel;
import com.neeejm.inventory.models.RoleModel;

@Component
public class RoleModelAssembler
        extends RepresentationModelAssemblerSupport<Role, RoleModel> {

    public RoleModelAssembler() {
        super(RoleContoller.class, RoleModel.class);
    }

    @Override
    public RoleModel toModel(Role role) {
        final String BASE_URL = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        RoleModel roleModel = createRessource(role);

        roleModel.add(Link.of(
                BASE_URL + DataRestConfig.BASE_PATH + "roles/{role_id}")
                .withSelfRel()
                .expand(role.getId()));
        roleModel.add(Link.of(
                BASE_URL + DataRestConfig.BASE_PATH + "roles/{role_id}")
                .withRel("role")
                .expand(role.getId()));
        roleModel.add(Link.of(
                BASE_URL + DataRestConfig.BASE_PATH + "roles/{role_id}/privileges")
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
                .build()).collect(Collectors.toSet());
    }
}

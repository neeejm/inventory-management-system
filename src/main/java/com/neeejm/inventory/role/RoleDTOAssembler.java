package com.neeejm.inventory.role;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeejm.inventory.privilege.PrivilegeDTO;
import com.neeejm.inventory.privilege.PrivilegeEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ComponentScan
public class RoleDTOAssembler
        extends RepresentationModelAssemblerSupport<RoleEntity, RoleDTO> {

    private String basePath;

    public RoleDTOAssembler() {
        super(RoleContoller.class, RoleDTO.class);
        basePath = "/api/v1/";
    }

    @Override
    public RoleDTO toModel(RoleEntity role) {

        RoleDTO roleModel = createRessource(role);
        

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

    private RoleDTO createRessource(RoleEntity role) {
        RoleDTO roleModel = instantiateModel(role);
        roleModel.setId(role.getId());
        roleModel.setName(role.getName());
        roleModel.setPrivileges(toPrivilegesModel(role.getPrivileges()));
        return roleModel;
    }

    private Set<PrivilegeDTO> toPrivilegesModel(Set<PrivilegeEntity> privileges) {
        if (privileges.isEmpty()) {
            return Collections.emptySet();
        }

        return privileges.stream().map(privilege -> PrivilegeDTO.builder()
                .id(privilege.getId())
                .name(privilege.getName())
                .build()
                .add(Link.of(
                    getBaseURL() + basePath + "privileges/{privilege_id}")
                    .withSelfRel()
                    .expand(privilege.getId()))
                .add(Link.of(
                    getBaseURL() + basePath + "privileges/{privilege_id}")
                    .withRel("privilege")
                    .expand(privilege.getId())))
                .collect(Collectors.toSet());
    }

    private String getBaseURL() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
    }
}

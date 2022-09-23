package com.neeejm.inventory.role.dto;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.common.util.Urls;
import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.dto.PrivilegeDTO;
import com.neeejm.inventory.role.RoleContoller;
import com.neeejm.inventory.role.RoleEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleDTOAssembler
        extends RepresentationModelAssemblerSupport<RoleEntity, RoleDTO> {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    public RoleDTOAssembler() {
        super(RoleContoller.class, RoleDTO.class);
        basePath = basePath == null ? "/api/v1/" : basePath;
    }

    @Override
    public RoleDTO toModel(RoleEntity role) {

        RoleDTO roleModel = createRessource(role);

        roleModel.add(Link.of(
                Urls.getBaseURL() + basePath + "roles/{role_id}")
                .withSelfRel()
                .expand(role.getId()));
        roleModel.add(Link.of(
                Urls.getBaseURL() + basePath + "roles/{role_id}")
                .withRel("role")
                .expand(role.getId()));
        roleModel.add(Link.of(
                Urls.getBaseURL() + basePath + "roles/{role_id}/privileges")
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
                    Urls.getBaseURL() + basePath + "privileges/{privilege_id}")
                    .withSelfRel()
                    .expand(privilege.getId()))
                .add(Link.of(
                    Urls.getBaseURL() + basePath + "privileges/{privilege_id}")
                    .withRel("privilege")
                    .expand(privilege.getId())))
                .collect(Collectors.toSet());
    }
}

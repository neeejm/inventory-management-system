package com.neeejm.inventory.privilege;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeejm.inventory.role.RoleContoller;

@Component
public class PrivilegeDTOAssembler
        extends RepresentationModelAssemblerSupport<PrivilegeEntity, PrivilegeDTO> {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    public PrivilegeDTOAssembler() {
        super(RoleContoller.class, PrivilegeDTO.class);
    }

    @Override
    public PrivilegeDTO toModel(PrivilegeEntity privilege) {

        PrivilegeDTO privilegeModel = createRessource(privilege);

        privilegeModel.add(Link.of(
                getBaseURL() + basePath + "privileges/{privilege_id}")
                .withSelfRel()
                .expand(privilege.getId()));
        privilegeModel.add(Link.of(
                getBaseURL() + basePath + "privileges/{privilege_id}")
                .withRel("privilege")
                .expand(privilege.getId()));

        return privilegeModel;
    }

    @Override
    public CollectionModel<PrivilegeDTO> toCollectionModel(Iterable<? extends PrivilegeEntity> privileges) {
        CollectionModel<PrivilegeDTO> privilegeModels = super.toCollectionModel(privileges);

        privilegeModels.add(Link.of(getRequestUrl())
                .withSelfRel());

        return privilegeModels;
    }

    private PrivilegeDTO createRessource(PrivilegeEntity privilege) {
        PrivilegeDTO privilegeModel = instantiateModel(privilege);

        privilegeModel.setId(privilege.getId());
        privilegeModel.setName(privilege.getName());
        return privilegeModel;
    }

    private String getBaseURL() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
    }

    private String getRequestUrl() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUriString();
    }
}

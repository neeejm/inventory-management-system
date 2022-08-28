package com.neeejm.inventory.models.assemblers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neeejm.inventory.controllers.RoleContoller;
import com.neeejm.inventory.entities.Privilege;
import com.neeejm.inventory.models.PrivilegeModel;

@Component
public class PrivilegeModelAssembler
        extends RepresentationModelAssemblerSupport<Privilege, PrivilegeModel> {

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    public PrivilegeModelAssembler() {
        super(RoleContoller.class, PrivilegeModel.class);
    }

    @Override
    public PrivilegeModel toModel(Privilege privilege) {

        PrivilegeModel privilegeModel = createRessource(privilege);

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
    public CollectionModel<PrivilegeModel> toCollectionModel(Iterable<? extends Privilege> privileges) {
        CollectionModel<PrivilegeModel> privilegeModels = super.toCollectionModel(privileges);

        privilegeModels.add(Link.of(getRequestUrl())
                .withSelfRel());

        return privilegeModels;
    }

    private PrivilegeModel createRessource(Privilege privilege) {
        PrivilegeModel privilegeModel = instantiateModel(privilege);

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

package com.neeejm.inventory.models.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.neeejm.inventory.controllers.RoleContoller;
import com.neeejm.inventory.entities.Privilege;
import com.neeejm.inventory.models.PrivilegeModel;

public class PrivilegeModelAssembler
        extends RepresentationModelAssemblerSupport<Privilege, PrivilegeModel> {

    public PrivilegeModelAssembler() {
        super(RoleContoller.class, PrivilegeModel.class);
    }

    @Override
    public PrivilegeModel toModel(Privilege privilege) {
        PrivilegeModel privilegeModel = instantiateModel(privilege);

        privilegeModel.setId(privilege.getId());
        privilegeModel.setName(privilege.getName());
        return privilegeModel;
    }
}

package com.neeejm.inventory.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neeejm.inventory.dtos.PrivilegeModel;
import com.neeejm.inventory.dtos.RoleModel;
import com.neeejm.inventory.dtos.assemblers.PrivilegeModelAssembler;
import com.neeejm.inventory.dtos.assemblers.RoleModelAssembler;
import com.neeejm.inventory.entities.Privilege;
import com.neeejm.inventory.entities.Role;
import com.neeejm.inventory.repositories.PrivilegeRepository;
import com.neeejm.inventory.repositories.RoleRepository;

// @BasePathAwareController
public class RoleContoller {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private PrivilegeModelAssembler privilegeAssembler;

    @Autowired
    public RoleContoller(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.privilegeAssembler = new PrivilegeModelAssembler();
    }

    @RequestMapping(
        path = "roles/{role_id}/privileges/{privilege_id}",
        method = RequestMethod.PATCH,
        produces = "application/hal+json"
    )
    public ResponseEntity<CollectionModel<PrivilegeModel>> appendPrivilege(
            @PathVariable("role_id") UUID roleId,
            @PathVariable("privilege_id") UUID privilegeId) {

        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
        if (!role.isPresent() || !privilege.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Role roleToPatch = role.get();
        roleToPatch.appendPrivilege(privilege.get());

        return ResponseEntity.ok(
                privilegeAssembler.toCollectionModel(
                        roleRepository.save(roleToPatch)
                        .getPrivileges()));
    }
}

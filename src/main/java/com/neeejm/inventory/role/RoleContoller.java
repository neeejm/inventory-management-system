package com.neeejm.inventory.role;

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

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeDTO;
import com.neeejm.inventory.privilege.PrivilegeDTOAssembler;
import com.neeejm.inventory.privilege.PrivilegeRepository;

// @BasePathAwareController
public class RoleContoller {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private PrivilegeDTOAssembler privilegeAssembler;

    @Autowired
    public RoleContoller(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.privilegeAssembler = new PrivilegeDTOAssembler();
    }

    // @RequestMapping(
    //     path = "roles/{role_id}/privileges/{privilege_id}",
    //     method = RequestMethod.PATCH,
    //     produces = "application/hal+json"
    // )
    public ResponseEntity<CollectionModel<PrivilegeDTO>> appendPrivilege(
            @PathVariable("role_id") UUID roleId,
            @PathVariable("privilege_id") UUID privilegeId) {

        Optional<RoleEntity> role = roleRepository.findById(roleId);
        Optional<PrivilegeEntity> privilege = privilegeRepository.findById(privilegeId);
        if (!role.isPresent() || !privilege.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        RoleEntity roleToPatch = role.get();
        roleToPatch.appendPrivilege(privilege.get());

        return ResponseEntity.ok(
                privilegeAssembler.toCollectionModel(
                        roleRepository.save(roleToPatch)
                        .getPrivileges()));
    }
}

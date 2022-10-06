package com.neeejm.inventory.role;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.role.dto.RoleDTO;
import com.neeejm.inventory.role.dto.RoleDTOAssembler;
import com.neeejm.inventory.role.service.RoleService;

@RepositoryRestController
public class RoleContoller {
    private final RoleService roleService;
    private RoleDTOAssembler roleAssembler;

    @Autowired
    public RoleContoller(final RoleService roleService) {
        this.roleService = roleService;
        this.roleAssembler = new RoleDTOAssembler();
    }

    @RequestMapping(
        path = "roles/{role_id}/privileges/{privilege_id}",
        method = RequestMethod.PATCH,
        produces = "application/hal+json"
    )
    public @ResponseBody ResponseEntity<RoleDTO> appendPrivilege(
            @PathVariable("role_id") UUID roleId,
            @PathVariable("privilege_id") UUID privilegeId) {

        RoleEntity role = roleService.appendPrivilegeToRole(privilegeId, roleId);
        return ResponseEntity.ok(roleAssembler.toModel(role));
    }

    @RequestMapping(
        path = "roles/{role_id}/privileges",
        method = RequestMethod.PATCH,
        produces = "application/hal+json"
    )
    public @ResponseBody ResponseEntity<RoleDTO> appendPrivileges(
        @PathVariable("role_id") UUID roleId,
        @RequestBody List<PrivilegeEntity> privileges) {

        RoleEntity role = roleService.appendPrivilegesToRole(privileges, roleId);
        return ResponseEntity.ok(roleAssembler.toModel(role));
    }
}

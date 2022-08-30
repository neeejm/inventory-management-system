package com.neeejm.inventory.role;

import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    // public @ResponseBody ResponseEntity<CollectionModel<PrivilegeDTO>> appendPrivilege(
    public @ResponseBody ResponseEntity<?> appendPrivilege(
            @PathVariable("role_id") UUID roleId,
            @PathVariable("privilege_id") UUID privilegeId) {

        try {

            RoleEntity role = roleService.appendPrivilegeToRole(privilegeId, roleId);
            return ResponseEntity.ok(roleAssembler.toModel(role));

        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityExistsException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}

package com.neeejm.inventory.role;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeRepository;

@Service
public class RoleServiceIml implements RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public RoleServiceIml(
            final RoleRepository roleRepository,
            final PrivilegeRepository privilegeRepository) {

        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public RoleEntity appendPrivilegeToRole(UUID privilegeId, UUID roleId) {

        PrivilegeEntity privilege = privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new EntityNotFoundException("Privilege with id: '" + privilegeId + "' not found."));
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with id: '" + roleId + "' not found"));

        if (role.getPrivileges().contains(privilege)) {
            throw new EntityExistsException(
                    "Role with id '" + roleId + "' already have privilege with id: '" + privilegeId + "'");
        }

        role.appendPrivilege(privilege);

        return roleRepository.save(role);
    }
    
}

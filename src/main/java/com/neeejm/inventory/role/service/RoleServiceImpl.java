package com.neeejm.inventory.role.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeEntity.Privilege;
import com.neeejm.inventory.privilege.PrivilegeRepository;
import com.neeejm.inventory.role.RoleEntity;
import com.neeejm.inventory.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public RoleServiceImpl(
            final RoleRepository roleRepository,
            final PrivilegeRepository privilegeRepository) {

        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public RoleEntity appendPrivilegeToRole(UUID privilegeId, UUID roleId) {

        RoleEntity role = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with id: '" + roleId + "' not found"));
        PrivilegeEntity privilege = privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new EntityNotFoundException("Privilege with id: '" + privilegeId + "' not found."));

        if (role.getPrivileges().contains(privilege)) {
            throw new EntityExistsException(
                    "Role with id '" + roleId + "' already have privilege with id: '" + privilegeId + "'");
        }

        role.appendPrivilege(privilege);

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity appendPrivilegesToRole(List<PrivilegeEntity> privileges, UUID roleId) {
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with id: '" + roleId + "' not found"));

        List<String> errorMessages = new ArrayList<>();

        privileges.forEach(p -> {
            privilegeRepository.findById(p.getId()).ifPresentOrElse(
                __ -> {
                    role.appendPrivilege(p);
                },
                ()-> errorMessages.add("Privilege with id: '" + p.getId() + "' not found."));
        });

        if (errorMessages.size() > 0) {
            throw new EntityNotFoundException(
                StringUtils.collectionToDelimitedString(errorMessages, ";\n"));
        }

        return roleRepository.save(role);
    }
    
}

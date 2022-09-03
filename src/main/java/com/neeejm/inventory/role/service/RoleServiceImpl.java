package com.neeejm.inventory.role.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeejm.inventory.common.exceptions.MutliEntityException;
import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeRepository;
import com.neeejm.inventory.role.RoleEntity;
import com.neeejm.inventory.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final String roleNotFoundMessage = "Role with id: '%s' not found";
    private final String privilegeNotFoundMessage = "Privilege with id: '%s' not found";
    private final String privilegeExistInRoleMessage = "Role with id '%s' already have privilege with id: '%s'";

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
                () -> new EntityNotFoundException(roleNotFoundMessage.formatted(roleId)));
        PrivilegeEntity privilege = privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new EntityNotFoundException(privilegeNotFoundMessage.formatted(privilegeId)));

        if (role.getPrivileges().contains(privilege)) {
            throw new EntityExistsException(privilegeExistInRoleMessage.formatted(roleId, privilegeId));
        }

        role.appendPrivilege(privilege);

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity appendPrivilegesToRole(List<PrivilegeEntity> privileges, UUID roleId) {
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException(roleNotFoundMessage.formatted(roleId)));

        Set<String> errorMessages = new HashSet<>();

        privileges.forEach(p -> {
            privilegeRepository.findById(p.getId()).ifPresentOrElse(
                __ -> {
                    if (role.getPrivileges().contains(p)) {
                        errorMessages.add(privilegeExistInRoleMessage.formatted(roleId, p.getId()));
                    }
                    role.appendPrivilege(p);
                },
                ()-> errorMessages.add(privilegeNotFoundMessage.formatted(p.getId()))
            );
        });

        if (errorMessages.size() > 0) {
            throw new MutliEntityException(errorMessages);
        }

        return roleRepository.save(role);
    }
}

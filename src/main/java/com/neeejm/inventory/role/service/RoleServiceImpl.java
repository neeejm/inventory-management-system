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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final String ROLE_NOT_FOUND_MESSAGE = "Role with id: '%s' not found";
    private final String PRIVILEGE_NOT_FOUND_MESSAGE = "Privilege with id: '%s' not found";
    private final String PRIVILEGE_EXISTS_IN_ROLE_MESSAGE = "Role with id '%s' already have " +
                                                            "privilege with id: '%s'";

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
                () -> new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE.formatted(roleId)));
        PrivilegeEntity privilege = privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new EntityNotFoundException(PRIVILEGE_NOT_FOUND_MESSAGE.formatted(privilegeId)));

        if (role.getPrivileges().contains(privilege)) {
            throw new EntityExistsException(PRIVILEGE_EXISTS_IN_ROLE_MESSAGE.formatted(roleId, privilegeId));
        }

        role.appendPrivilege(privilege);

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity appendPrivilegesToRole(List<PrivilegeEntity> privileges, UUID roleId) {
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE.formatted(roleId)));

        Set<String> errorMessages = new HashSet<>();

        privileges.forEach(p ->
            privilegeRepository.findById(p.getId()).ifPresentOrElse(
                    __ -> {
                        if (role.getPrivileges().contains(p)) {
                            errorMessages.add(PRIVILEGE_EXISTS_IN_ROLE_MESSAGE.formatted(roleId, p.getId()));
                        }
                        role.appendPrivilege(p);
                    },
                    () -> errorMessages.add(PRIVILEGE_NOT_FOUND_MESSAGE.formatted(p.getId())))
        );

        if (!errorMessages.isEmpty()) {
            throw new MutliEntityException(errorMessages);
        }

        return roleRepository.save(role);
    }
}

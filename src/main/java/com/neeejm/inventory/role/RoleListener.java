package com.neeejm.inventory.role;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.BeforeLinkSaveEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(RoleEntity.class)
public class RoleListener {

    private final String READ_ONLY_MSG = "Role '%s' with id '%s' is read only.";
    private final String EXISTS_MSG = "Role '%s' already exists.";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityManager entityManager;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(RoleEntity roleToCreate) {
        log.info("[ROLE_CHECK]");

        roleToCreate.setName(roleToCreate.getName().toUpperCase());

        Optional<RoleEntity> role = roleRepository.findByName(roleToCreate.getName());
        if (role.isPresent()) {
            log.error("[ROLE_EXISTS] Role: {}", roleToCreate.getName());
            throw new EntityExistsException(EXISTS_MSG.formatted(roleToCreate.getName()));
        }
    }

    @HandleBeforeDelete
    @HandleBeforeSave
    public void doNotUpdateOrDeleteDefaultRoles(RoleEntity role) {
        entityManager.detach(role);

        RoleEntity currentRole = roleRepository.findById(role.getId()).orElseThrow();
        Arrays.stream(RoleEntity.Role.values()).forEach(r -> {
            if (currentRole.getName().equals(r.toString())) {
                log.info("[DEFAULT_ROLE] " + READ_ONLY_MSG.formatted(role.getName(), role.getId()));

                throw new ReadOnlyRoleException(READ_ONLY_MSG.formatted(role.getName(),
                        role.getId()));
            }
        });

        role.setName(role.getName().toUpperCase());
    }
}

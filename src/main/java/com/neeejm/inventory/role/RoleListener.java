package com.neeejm.inventory.role;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler(RoleEntity.class)
public class RoleListener {

    private final String READ_ONLY_MSG = "Role '%s' with id '%s' is read only.";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityManager entityManager;

    @HandleBeforeCreate
    public void changeRoleNameToUpperCase(RoleEntity role) {
        role.setName(role.getName().toUpperCase());
        log.info("[ROLE_CREATED] Role: {}", role.getName());
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

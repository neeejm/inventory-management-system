package com.neeejm.inventory.configs;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.neeejm.inventory.models.Privilege;
import com.neeejm.inventory.models.Role;
import com.neeejm.inventory.repositories.PrivilegeRepository;
import com.neeejm.inventory.repositories.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppConfig {
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        loadPrivileges();
        loadDefaultRoles();
    }

    private void loadPrivileges() {
        if (privilegeRepository.findAll().size() == 0) {
            Arrays.stream(Privilege.PrivilegeName.values()).forEach(
                    p -> {
                        privilegeRepository.save(
                                Privilege.builder()
                                        .name(p.toString())
                                        .build());
                        log.info("SEED_PRIVILEGES ==> " + p.toString());
                    });
            log.info("Privileges seeding done");
        } else {
            log.info("Privileges seeding not required");
        }
    }

    private void loadDefaultRoles() {
        if (roleRepository.findAll().size() == 0) {
            saveAdminRole();
            savePurchasesRole();
            saveSalesRole();
            log.info("Roles seeding done");
        } else {
            log.info("Roles seeding not required");
        }
    }

    private void saveAdminRole() {
        Role adminRole = Role.builder()
                .name(Role.RoleName.ROLE_ADMIN.toString())
                .privileges(Set.of(
                        privilegeRepository.findByName(
                            Privilege.PrivilegeName.OP_ALL.toString()
                        ).get()
                ))
                .build();
        roleRepository.save(adminRole);
    }

    private void saveSalesRole() {
        Role salesManagerRole = Role.builder()
                .name(Role.RoleName.ROLE_SALES_MANAGER.toString())
                .privileges(Set.of(
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_CREATE_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_DELETE_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_READ_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_UPDATE_SALES.toString()
                    ).get()
                ))
                .build();
        roleRepository.save(salesManagerRole);
    }

    private void savePurchasesRole() {
        Role purchasesManagerRole = Role.builder()
                .name(Role.RoleName.ROLE_PURCHASES_MANAGER.toString())
                .privileges(Set.of(
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_CREATE_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_DELETE_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_READ_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        Privilege.PrivilegeName.OP_UPDATE_PURCHASES.toString()
                    ).get()
                ))
                .build();
        roleRepository.save(purchasesManagerRole);
    }
}

package com.neeejm.inventory.role;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleSeeder {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    @EventListener
    @Order(2)
    public void seed(ContextRefreshedEvent event) {
        loadDefaultRoles();
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
        RoleEntity adminRole = RoleEntity.builder()
                .name(RoleEntity.Role.ROLE_ADMIN.toString())
                .privileges(Set.of(
                        privilegeRepository.findByName(
                            PrivilegeEntity.Privilege.OP_ALL.toString()
                        ).get()
                ))
                .build();
        roleRepository.save(adminRole);
    }

    private void saveSalesRole() {
        RoleEntity salesManagerRole = RoleEntity.builder()
                .name(RoleEntity.Role.ROLE_SALES_MANAGER.toString())
                .privileges(Set.of(
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_CREATE_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_DELETE_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_READ_SALES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_UPDATE_SALES.toString()
                    ).get()
                ))
                .build();
        roleRepository.save(salesManagerRole);
    }

    private void savePurchasesRole() {
        RoleEntity purchasesManagerRole = RoleEntity.builder()
                .name(RoleEntity.Role.ROLE_PURCHASES_MANAGER.toString())
                .privileges(Set.of(
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_CREATE_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_DELETE_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_READ_PURCHASES.toString()
                    ).get(),
                    privilegeRepository.findByName(
                        PrivilegeEntity.Privilege.OP_UPDATE_PURCHASES.toString()
                    ).get()
                ))
                .build();
        roleRepository.save(purchasesManagerRole);
    }
}

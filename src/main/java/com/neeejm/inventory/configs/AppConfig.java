package com.neeejm.inventory.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

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

    private void loadDefaultRoles() {
        if (roleRepository.findAll().size() == 0) {
            Arrays.stream(Role.RoleName.values()).forEach(
                    r -> {
                        roleRepository.save(
                                Role.builder()
                                        .name(r.toString())
                                        .build());
                        log.info("SEED_ROLES ==> " + r.toString());
                    });
            log.info("Roles seeding done");
        } else {
            log.info("Roles seeding not required");
        }
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
}

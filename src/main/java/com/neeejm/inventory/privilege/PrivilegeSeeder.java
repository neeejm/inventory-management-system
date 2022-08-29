package com.neeejm.inventory.privilege;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrivilegeSeeder {
    
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        loadPrivileges();
    }

    private void loadPrivileges() {
        if (privilegeRepository.findAll().size() == 0) {
            Arrays.stream(PrivilegeEntity.PrivilegeName.values()).forEach(
                    p -> {
                        privilegeRepository.save(
                                PrivilegeEntity.builder()
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

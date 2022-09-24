package com.neeejm.inventory.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.role.RoleEntity;
import com.neeejm.inventory.role.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSeeder {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    @Order(3)
    public void seed(ContextRefreshedEvent event) {
        loadUsers();
    }

    private void loadUsers() {
        if (userRepository.findAll().isEmpty()) {
            saveAdminUser();
            log.info("[SEED] Users seeding done");
        } else {
            log.info("[SEED] Users seeding not required");
        }
    }

    private void saveAdminUser() {
        UserEntity user = UserEntity.builder()
                .email("admin@mail.com")
                .firstName("fadmin")
                .lastName("ladmin")
                .username("admin")
                .password("admin")
                .roles(Set.of(
                    roleRepository.findByName(
                        RoleEntity.Role.ROLE_ADMIN.toString()
                    ).orElseThrow()
                ))
                .build();
        userRepository.save(user);
    }
}

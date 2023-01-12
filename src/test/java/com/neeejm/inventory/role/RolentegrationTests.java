package com.neeejm.inventory.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.neeejm.inventory.common.errors.ApiError;
import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeRepository;
import com.neeejm.inventory.role.dto.RoleDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolentegrationTests {

    private static final String APPLICATION_HAL_JSON = "application/hal+json";
    private static final String APPLICATION_JSON = "application/json";

    private final String ROLE_NOT_FOUND_MESSAGE = "Role with id: '%s' not found";

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    private RoleEntity role;
    private PrivilegeEntity privilege;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private WebTestClient wClient;

    @BeforeEach
    void setup() {
        privilege = privilegeRepository.findByName(PrivilegeEntity.Privilege.OP_ALL.toString()).get();

        role = RoleEntity.builder()
                        .name("role_integration_test")
                        .build();

        log.debug("[TEST] Role({}, {}): ", role.getId(), role.getName());
    }

    @Test
    void shouldReturnRoleOnAppendPrivilege() {
        // Given
        role = roleRepository.save(role);
        String url = basePath + "/roles/{role_id}/privileges/{privilege_id}";

        // When
        // Then
        wClient.patch()
                .uri(url, role.getId(), privilege.getId())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_HAL_JSON)
                .expectBody(RoleDTO.class).value(r -> {
                    assertThat(r.getId()).isEqualTo(role.getId());
                    assertThat(r.getPrivileges()).isNotEmpty().hasSize(1)
                            .extracting(p -> p.getId()).first().isEqualTo(privilege.getId());
                });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnEntityNotFoundOnAppendPrivilege() {
        // Given
        String url = basePath + "/roles/{role_id}/privileges/{privilege_id}";
        UUID randomId = UUID.randomUUID();

        // When
        // Then
        wClient.patch()
                .uri(url, randomId, privilege.getId())
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(ApiError.class).value(e -> {
                    assertThat(e.getErrors()).isNotEmpty().hasSize(1)
                            .containsExactly(ROLE_NOT_FOUND_MESSAGE.formatted(randomId));
                });
    }

    @Test
    @SuppressWarnings("unchecked")
    void testValidation() {
        // Given
        String url = basePath + "/roles";
        RoleEntity notAValidRole = RoleEntity.builder()
                                            .name("role_")
                                            .build();

        // When
        // Then
        wClient.post()
                .uri(url)
                .body(Mono.just(notAValidRole), RoleEntity.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(ApiError.class).value(e -> {
                    assertThat(e.getErrors()).isNotEmpty().hasSize(1);
                });
    }
}

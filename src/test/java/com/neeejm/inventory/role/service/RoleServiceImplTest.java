package com.neeejm.inventory.role.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.privilege.PrivilegeRepository;
import com.neeejm.inventory.role.RoleEntity;
import com.neeejm.inventory.role.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PrivilegeRepository privilegeRepository;
    @Captor
    private ArgumentCaptor<RoleEntity> roleArgCaptor;
    private RoleEntity role;
    private PrivilegeEntity privilege;

    private RoleService serviceUnderTest;

    @BeforeEach
    void setup() {
        serviceUnderTest = new RoleServiceImpl(roleRepository, privilegeRepository);

        privilege = PrivilegeEntity.builder()
                    .id(UUID.randomUUID())
                    .name(PrivilegeEntity.Privilege.OP_CREATE_PRODUCTS.name())
                    .build();

        role = RoleEntity.builder()
                .id(UUID.randomUUID())
                .name("role_test")
                .build();
    }

    @Test
    @DisplayName("Should append privilege to role")
    void testAppendPrivilegeToRole() {
        // Given
        given(roleRepository.findById(role.getId())).willReturn(Optional.of(role));
        given(privilegeRepository.findById(privilege.getId())).willReturn(Optional.of(privilege));

        // When 
        serviceUnderTest.appendPrivilegeToRole(privilege.getId(), role.getId());

        // Then
        verify(roleRepository).save(roleArgCaptor.capture());
        RoleEntity capturedRole = roleArgCaptor.getValue();
        assertThat(capturedRole.getId()).isEqualTo(role.getId());
        assertThat(capturedRole.getPrivileges()).isNotEmpty().hasSize(1);
        assertThat(capturedRole.getPrivileges()).contains(privilege);
    }
}

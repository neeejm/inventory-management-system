package com.neeejm.inventory.role.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
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
public class RoleServiceImplTests {

    private final String ROLE_NOT_FOUND_MESSAGE = "Role with id: '%s' not found";
    private final String PRIVILEGE_NOT_FOUND_MESSAGE = "Privilege with id: '%s' not found";
    private final String PRIVILEGE_EXISTS_IN_ROLE_MESSAGE = "Role with id '%s' already have privilege with id: '%s'";

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PrivilegeRepository privilegeRepository;

    @Captor
    private ArgumentCaptor<RoleEntity> roleArgCaptor;

    private RoleEntity role;
    private RoleEntity roleWithPrivilege;
    private PrivilegeEntity privilege;

    private RoleService serviceUnderTest;

    @BeforeEach
    void setup() {
        serviceUnderTest = new RoleServiceImpl(roleRepository, privilegeRepository);

        // Given
        privilege = PrivilegeEntity.builder()
                .id(UUID.randomUUID())
                .name(PrivilegeEntity.Privilege.OP_CREATE_PRODUCTS.name())
                .build();

        role = RoleEntity.builder()
                .id(UUID.randomUUID())
                .name("role_test")
                .build();

        roleWithPrivilege = RoleEntity.builder()
                .id(UUID.randomUUID())
                .name("role_test_with_priv")
                .privileges(Set.of(privilege))
                .build();
    }

    @Test
    void shouldAppendPrivilegeToRole() {
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

    @Test
    void shouldThrowRoleEntityNotFoundOnAppendPrivilege() {
        // When
        Exception expectedException = catchException(() -> {
            serviceUnderTest.appendPrivilegeToRole(privilege.getId(), role.getId());
        });

        // Then
        assertThat(expectedException).isInstanceOf(EntityNotFoundException.class)
                .hasMessage(ROLE_NOT_FOUND_MESSAGE.formatted(role.getId()));
    }

    @Test
    void shouldThrowPrivilegeEntityNotFoundOnAppendPrivilege() {
        // Given
        given(roleRepository.findById(any())).willReturn(Optional.of(role));

        // When
        Exception expectedException = catchException(() -> {
            serviceUnderTest.appendPrivilegeToRole(privilege.getId(), role.getId());
        });

        // Then
        assertThat(expectedException).isInstanceOf(EntityNotFoundException.class)
                .hasMessage(PRIVILEGE_NOT_FOUND_MESSAGE.formatted(privilege.getId()));
    }

    @Test
    void shouldThrowEntityExistsOnAppendPrivilege() {
        // Given
        given(roleRepository.findById(any())).willReturn(Optional.of(roleWithPrivilege));
        given(privilegeRepository.findById(any())).willReturn(Optional.of(privilege));

        // When
        Exception expectedException = catchException(() -> {
            serviceUnderTest.appendPrivilegeToRole(privilege.getId(), roleWithPrivilege.getId());
        });

        // Then
        assertThat(expectedException).isInstanceOf(EntityExistsException.class)
                .hasMessage(PRIVILEGE_EXISTS_IN_ROLE_MESSAGE.formatted(
                        roleWithPrivilege.getId(), privilege.getId()));
    }
}

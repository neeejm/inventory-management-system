package com.neeejm.inventory.role.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RoleNameValidatorTest {

    private static final RoleNameValidator roleNameValidator = new RoleNameValidator();

    @ParameterizedTest
    @ValueSource(strings = {
        "role_tEST",
        "role_hello_test_world",
        "role_azertyuiopqs"
    })
    void shouldBeAValidRoleName() {
        String roleName = "ROLE_test";

        boolean expected =  roleNameValidator.isValid(roleName, null);

        assertThat(expected).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "rOle_",
        "role_ROle",
        "RoLe_supposedtobealongword",
        "role",
        "role_h",
        "role_repeat_repeat_repeat_repeat",
        "notrole_test",
        "role_eh9"
    })
    void shouldNotBeAValidRoleName(String roleName) {

        boolean expected =  roleNameValidator.isValid(roleName, null);

        assertThat(expected).isFalse();
    }
}

package com.neeejm.inventory.role.service;

import java.util.UUID;

import com.neeejm.inventory.role.RoleEntity;

public interface RoleService {
    RoleEntity appendPrivilegeToRole(UUID privilegeId, UUID roleId);
}

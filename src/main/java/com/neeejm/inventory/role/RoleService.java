package com.neeejm.inventory.role;

import java.util.UUID;

public interface RoleService {
    RoleEntity appendPrivilegeToRole(UUID privilegeId, UUID roleId);
}

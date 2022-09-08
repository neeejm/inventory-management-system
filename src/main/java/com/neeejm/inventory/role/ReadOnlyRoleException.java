package com.neeejm.inventory.role;

public class ReadOnlyRoleException extends RuntimeException {

    public ReadOnlyRoleException(String msg) {
        super(msg);
    }

}

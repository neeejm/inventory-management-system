package com.neeejm.inventory.common.exceptions;

import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.util.StringUtils;

import lombok.Getter;

// better naming ???
public class MutliEntityException extends PersistenceException {

    @Getter
    private Set<String> messages;
    
    public MutliEntityException(Set<String> messages) {
        this.messages = messages;
    }

    @Override
    public String getMessage() {
        return StringUtils.collectionToCommaDelimitedString(messages);
    }
}

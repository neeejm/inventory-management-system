package com.neeejm.inventory.services;

import com.neeejm.inventory.models.User;
import com.neeejm.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<UserRepository, User>
        implements UserService {

    @Autowired
    protected UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}

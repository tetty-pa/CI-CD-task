package com.epam.esm.repository.impl;

import com.epam.esm.repository.AbstractRepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class, "users");
    }
}

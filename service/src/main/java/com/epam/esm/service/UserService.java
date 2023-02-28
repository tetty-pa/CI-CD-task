package com.epam.esm.service;

import com.epam.esm.model.entity.Tag;
import com.epam.esm.model.entity.User;
import com.epam.esm.exception.EntityNotFoundException;

import java.util.List;

public interface UserService {
    /**
     * Gets all Users.
     *
     * @param page page number for pagination
     * @param size   page size for pagination
     * @return List of Users
     */
    List<User> getAll(int page, int size) ;
    /**
     * Gets User by id.
     *
     * @param id User id to get
     * @return User
     */
    User getById(long id) throws EntityNotFoundException;
    /**
     * Creates new User.
     *
     * @param user User to create
     * @return User
     */
    User create(User user);


}

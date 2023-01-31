package com.epam.esm.service;

import com.epam.esm.entity.User;
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

}

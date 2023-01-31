package com.epam.esm.dao;

import com.epam.esm.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Gets all Users.
     *
     * @param pageable object with pagination info(page number, page size)
     * @return List of Users
     */
    List<User> getAll(Pageable pageable);

    /**
     * Gets User by id.
     *
     * @param id User id to get
     * @return Optional<User> User if founded or Empty if not
     */
    Optional<User> getById(long id);

}

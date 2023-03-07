package com.epam.esm.repository;

import com.epam.esm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Gets User by  name.
     *
     * @param name User name to get
     * @return Optional<User> user if founded or Empty if not
     */
    Optional<User> findByName(String name);

}

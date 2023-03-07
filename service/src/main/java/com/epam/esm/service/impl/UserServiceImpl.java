package com.epam.esm.service.impl;


import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.model.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return userRepository.findAll(pageRequest).getContent();
    }

    @Override
    public User getById(long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
    }

    @Override
    public User create(User user) {
        boolean isUserExist = userRepository.findByName(user.getName()).isPresent();
        if (isUserExist) {
            throw new DuplicateEntityException("user.already.exist");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


}

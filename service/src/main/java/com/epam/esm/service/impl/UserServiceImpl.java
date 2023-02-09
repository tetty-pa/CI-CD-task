package com.epam.esm.service.impl;


import com.epam.esm.repository.UserRepository;
import com.epam.esm.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return userRepository.getAll(pageRequest);
    }

    @Override
    public User getById(long id) throws EntityNotFoundException {
        return userRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
    }
}

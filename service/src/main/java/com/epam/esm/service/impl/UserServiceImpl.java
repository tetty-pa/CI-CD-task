package com.epam.esm.service.impl;


import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return userDao.getAll(pageRequest);
    }

    @Override
    public User getById(long id) throws EntityNotFoundException {
        return userDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.repository.UserRepository;
import com.epam.esm.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.service.impl.util.Constants.*;
import static com.epam.esm.service.impl.util.Constants.PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
/*

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getAll() {
        List<User> expected = Arrays.asList(FIRST_TEST_USER, SECOND_TEST_USER);
        when(userRepository.getAll(PAGE)).thenReturn(expected);
        List<User> actual = userService.getAll(PAGE_NUM, PAGE_SIZE);

        assertEquals(expected, actual);

    }

    @Test
    void getById() {
        when(userRepository.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_USER));
        User actual = userService.getById(TEST_ID);
        assertEquals(FIRST_TEST_USER, actual);
    }

    @Test
    void getByIdShouldThrowEntityNotFoundException() {
        when(userRepository.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> userService.getById(NOT_EXIST_ID));
    }
}*/

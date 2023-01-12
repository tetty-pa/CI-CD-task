package com.epam.esm.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    private final long TEST_ID = 1;
    private final long INVALID_TEST_ID = 100;

    private final static Tag FIRST_TEST_TAG = new Tag(1, "1");
    private final static Tag SECOND_TEST_TAG = new Tag(2, "2");
    private final static Tag THIRD_TEST_TAG = new Tag(3, "3");

    private final static Tag TAG_TO_CREATE = new Tag(4, "new");
    private final static Tag INVALID_TAG = new Tag(5, "");


    @Mock
    TagDao tagDao;

    @InjectMocks
    TagServiceImpl tagService;

    @Test
    void getAll() {
        List<Tag> tags = Arrays.asList(FIRST_TEST_TAG, SECOND_TEST_TAG, THIRD_TEST_TAG);
        when(tagDao.getAll()).thenReturn(tags);
        List<Tag> actual = tagService.getAll();

        assertEquals(tags, actual);
    }

    @Test
    void create() {
        when(tagDao.create(TAG_TO_CREATE)).thenReturn(Optional.of(TAG_TO_CREATE));
        Tag actual = tagService.create(TAG_TO_CREATE);
        assertEquals(TAG_TO_CREATE, actual);
    }

    @Test
    void createShouldThrowInvalidDataException() {
        when(tagDao.create(INVALID_TAG)).thenThrow(new InvalidDataException());
        assertThrows(InvalidDataException.class, () -> tagService.create(INVALID_TAG));
    }

    @Test
    void getById() {
        when(tagDao.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_TAG));
        Tag actual = tagService.getById(TEST_ID);
        assertEquals(FIRST_TEST_TAG, actual);
    }

    @Test
    void getByIdShouldThrowEntityNotFoundException() {
        when(tagDao.getById(INVALID_TEST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> tagService.getById(INVALID_TEST_ID));
    }

    @Test
    void deleteById() {
        when(tagDao.deleteById(TEST_ID)).thenReturn(true);
        boolean result = tagService.deleteById(TEST_ID);
        assertTrue(result);
    }
    @Test
    void deleteByIdShouldThrowEntityNotFoundException() {
        when(tagDao.deleteById(INVALID_TEST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> tagService.deleteById(INVALID_TEST_ID));
    }
}
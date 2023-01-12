package com.epam.esm.dao.impl;

import com.epam.esm.config.JdbcConfigTest;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ContextConfiguration(classes = JdbcConfigTest.class)
@ExtendWith(SpringExtension.class)
@SqlGroup({
        @Sql(value = "classpath:createDB.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:fillDB.sql", executionPhase = BEFORE_TEST_METHOD)
})
class TagDaoImplTest {
    private static final String TEST_NAME = "1";
    private static final long TEST_ID = 1;
    final TagDaoImpl tagDao;


    private final static Tag FIRST_TEST_TAG = new Tag(1, "1");
    private final static Tag SECOND_TEST_TAG = new Tag(2, "2");
    private final static Tag THIRD_TEST_TAG = new Tag(3, "3");

    private final static Tag TAG_TO_CREATE = new Tag(4, "new");

    @Autowired
    public TagDaoImplTest(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Test
    void getAll() {
        List<Tag> actual = Arrays.asList(FIRST_TEST_TAG, SECOND_TEST_TAG, THIRD_TEST_TAG);
        List<Tag> expected = tagDao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getByColumn() {
        Optional<Tag> tag = tagDao.getByColumn("name", TEST_NAME);
        assertEquals(FIRST_TEST_TAG, tag.get());
    }

    @Test
    void getById() {
        assertEquals(FIRST_TEST_TAG, tagDao.getById(TEST_ID).get());
    }

    @Test
    void deleteById() {
        assertTrue(tagDao.deleteById(TEST_ID));
    }

    @Test
    void create() {
        Optional<Tag> tag = tagDao.create(TAG_TO_CREATE);
        assertTrue(tag.isPresent());
    }

    @Test
    void getAllByGiftCertificateId() {
        List<Tag> actual = Arrays.asList(FIRST_TEST_TAG, SECOND_TEST_TAG);
        List<Tag> expected = tagDao.getAllByGiftCertificateId(TEST_ID);
        assertEquals(expected, actual);

    }
}
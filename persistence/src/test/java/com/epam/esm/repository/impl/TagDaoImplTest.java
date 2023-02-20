package com.epam.esm.repository.impl;

import com.epam.esm.config.Config;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.epam.esm.repository.impl.util.Constants.FIRST_TEST_TAG;
import static org.junit.jupiter.api.Assertions.*;

/*
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {Config.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
class TagDaoImplTest {

    private final TagRepositoryImpl tagDao;

    @Autowired
    public TagDaoImplTest(TagRepositoryImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Test
    void getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders() {
        Optional<Tag> actual = tagDao.getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(1);
        assertEquals(Optional.of(FIRST_TEST_TAG), actual);
    }
}*/

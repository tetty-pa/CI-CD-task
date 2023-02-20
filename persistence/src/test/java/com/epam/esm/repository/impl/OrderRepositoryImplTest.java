package com.epam.esm.repository.impl;

import com.epam.esm.config.Config;
import com.epam.esm.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
/*
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {Config.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
class OrderRepositoryImplTest {

    private final OrderRepositoryImpl orderDao;

    @Autowired
    public OrderRepositoryImplTest(OrderRepositoryImpl orderDao) {
        this.orderDao = orderDao;
    }

    @Test
    void getAllByUserId() {
        List<Order> actual = orderDao.getAllByUserId(1, PageRequest.of(0, 25));
        List<Order> expected = List.of(FIRST_TEST_ORDER, SECOND_TEST_ORDER);
        assertEquals(expected, actual);
    }
}*/

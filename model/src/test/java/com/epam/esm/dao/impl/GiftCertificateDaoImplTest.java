package com.epam.esm.dao.impl;

import com.epam.esm.config.JdbcConfigTest;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ContextConfiguration(classes = JdbcConfigTest.class)
@ComponentScan("com.epam.esm")
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SqlGroup({
        @Sql(value = "classpath:createDB.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:fillDB.sql", executionPhase = BEFORE_TEST_METHOD)
})
class GiftCertificateDaoImplTest {

    private final GiftCertificateDaoImpl giftCertificateDao;
    private final static int TEST_ID = 1;
    private final static String TEST_NAME = "1";
    private final static GiftCertificate FIRST_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(1, "1", "1", new BigDecimal(1),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);
    private final static GiftCertificate SECOND_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(2, "2", "2", new BigDecimal(2),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 2);

    private final static GiftCertificate THIRD_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(3, "3", "3", new BigDecimal(3),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 3);
    private final static GiftCertificate GIFT_CERTIFICATE_TO_CREATE  = new GiftCertificate(
            4L, "certificate new", "description new", new BigDecimal("1.10"),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);


    @Autowired
    GiftCertificateDaoImplTest(GiftCertificateDaoImpl giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;

    }


    @Test
    void update() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "new name");
        giftCertificateDao.update(1, stringObjectMap);
    }

    @Test
    void addTagToGiftCertificate() {
        assertTrue(giftCertificateDao.addTagToGiftCertificate(TEST_ID, 3));
    }


    @Test
    void getGiftCertificatesByParameters() {
        QueryParameters parameters = new QueryParameters("1", null, null, null, null);
        List<GiftCertificate> actual = giftCertificateDao.getGiftCertificatesByParameters(parameters);
        List<GiftCertificate> expected = List.of(FIRST_TEST_GIFT_CERTIFICATE);
        assertEquals(expected, actual);
    }


    @Test
    void getAll() {
        List<GiftCertificate> actual = giftCertificateDao.getAll();

        List<GiftCertificate> expected =
                Arrays.asList(FIRST_TEST_GIFT_CERTIFICATE, SECOND_TEST_GIFT_CERTIFICATE, THIRD_TEST_GIFT_CERTIFICATE);
        assertEquals(expected, actual);
    }

    @Test
    void getByColumn() {
        Optional<GiftCertificate> actual = giftCertificateDao.getByColumn("name", TEST_NAME);
        assertEquals(FIRST_TEST_GIFT_CERTIFICATE, actual.get());
    }

    @Test
    void getById() {
        assertEquals(FIRST_TEST_GIFT_CERTIFICATE, giftCertificateDao.getById(TEST_ID).get());
    }

    @Test
    void deleteById() {
        assertTrue(giftCertificateDao.deleteById(TEST_ID));
    }

    @Test
    void create() {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.create(GIFT_CERTIFICATE_TO_CREATE);
        assertTrue(giftCertificate.isPresent());
    }
}
package com.epam.esm.repository.impl;

import com.epam.esm.config.Config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {Config.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DirtiesContext
class GiftCertificateDaoImplTest {
/*
    private final GiftCertificateDaoImpl giftCertificateDao;


    @Autowired
    GiftCertificateDaoImplTest(GiftCertificateDaoImpl giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }


    @Test
    void getGiftCertificatesByParameters() {
        QueryParameters queryParameters = new QueryParameters("1", null, List.of("1"), null, null);
        List<GiftCertificate> giftCertificatesByParameters = giftCertificateDao.getGiftCertificatesByParameters(queryParameters, PAGE);
        assertEquals(giftCertificatesByParameters, List.of(FIRST_TEST_GIFT_CERTIFICATE));
    }

    @Test
    void create() {
        GiftCertificate actual = giftCertificateDao.create(GIFT_CERTIFICATE_TO_CREATE);
        assertEquals(GIFT_CERTIFICATE_TO_CREATE, actual);
    }

    @Test
    void getAll() {
        List<GiftCertificate> actual = giftCertificateDao.getAll(PAGE);
        List<GiftCertificate> expected = List.of(FIRST_TEST_GIFT_CERTIFICATE, SECOND_TEST_GIFT_CERTIFICATE, THIRD_TEST_GIFT_CERTIFICATE);
        assertEquals(expected, actual);
    }

    @Test
    void getByColumn() {
        Optional<GiftCertificate> actual = giftCertificateDao.getByColumn("name", TEST_NAME);
        assertEquals(Optional.of(FIRST_TEST_GIFT_CERTIFICATE), actual);
    }

    @Test
    void update() {
        GiftCertificate actual = giftCertificateDao.update(GIFT_CERTIFICATE_TO_CREATE);
        assertEquals(GIFT_CERTIFICATE_TO_CREATE, actual);
    }

    @Test
    void getById() {
        Optional<GiftCertificate> actual = giftCertificateDao.getById(TEST_ID);
        assertEquals(Optional.of(FIRST_TEST_GIFT_CERTIFICATE), actual);
    }

    @Test
    void deleteById() {
        giftCertificateDao.deleteById(TEST_ID);
        Optional<GiftCertificate> actual = giftCertificateDao.getById(TEST_ID);
        assertEquals(Optional.empty(), actual);
    }*/
}
package com.epam.esm.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    private final static int TEST_ID = 1;
    private final static int NOT_EXIST_ID = 100;

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
    private final static GiftCertificate GIFT_CERTIFICATE_TO_CREATE = new GiftCertificate(
            4L, "hi ", "description new", new BigDecimal("1.10"),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);

    private final static GiftCertificate INVALID_GIFT_CERTIFICATE = new GiftCertificate(
            4L, "", "description new", new BigDecimal("1.10"),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);

    private final static GiftCertificate UPDATED_GIFT_CERTIFICATE = new GiftCertificate(
            1, "new name", "1", new BigDecimal("1"),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);
    @Mock
    private GiftCertificateDaoImpl giftCertificateDao;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @Test
    void getAll() {
        List<GiftCertificate> giftCertificates = Arrays.asList(FIRST_TEST_GIFT_CERTIFICATE, SECOND_TEST_GIFT_CERTIFICATE, THIRD_TEST_GIFT_CERTIFICATE);
        when(giftCertificateDao.getAll()).thenReturn(giftCertificates);
        List<GiftCertificate> actual = giftCertificateService.getAll();

        assertEquals(giftCertificates, actual);
    }

    @Test
    void getGiftCertificatesByParameters() {
        List<GiftCertificate> giftCertificates = List.of(FIRST_TEST_GIFT_CERTIFICATE);
        QueryParameters parameters = new QueryParameters("1", null, "1", null, null);
        when(giftCertificateDao.getGiftCertificatesByParameters(parameters)).thenReturn(giftCertificates);
        List<GiftCertificate> actual = giftCertificateService.getGiftCertificatesByParameters(parameters);

        assertEquals(giftCertificates, actual);
    }

    @Test
    void getById() {
        when(giftCertificateDao.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_GIFT_CERTIFICATE));
        GiftCertificate actual = giftCertificateService.getById(TEST_ID);
        assertEquals(FIRST_TEST_GIFT_CERTIFICATE, actual);
    }

    @Test
    void getByIdShouldThrowEntityNotFoundException() {
        when(giftCertificateDao.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.getById(NOT_EXIST_ID));
    }

    @Test
    void create() {
        when(giftCertificateDao.create(GIFT_CERTIFICATE_TO_CREATE)).thenReturn(Optional.of(GIFT_CERTIFICATE_TO_CREATE));
        when(giftCertificateDao.getByColumn("name", GIFT_CERTIFICATE_TO_CREATE.getName())).thenReturn(Optional.empty())
                .thenReturn(Optional.of(GIFT_CERTIFICATE_TO_CREATE));
        GiftCertificate actual = giftCertificateService.create(GIFT_CERTIFICATE_TO_CREATE);
        assertEquals(GIFT_CERTIFICATE_TO_CREATE, actual);
    }

    @Test
    void createShouldThrowInvalidDataException() {
        when(giftCertificateDao.create(INVALID_GIFT_CERTIFICATE)).thenThrow(new InvalidDataException());
        assertThrows(InvalidDataException.class, () -> giftCertificateService.create(INVALID_GIFT_CERTIFICATE));
    }

    @Test
    void update() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "new name");
        when(giftCertificateDao.update(UPDATED_GIFT_CERTIFICATE.getId(), stringObjectMap)).thenReturn(true);
        when(giftCertificateDao.getById(UPDATED_GIFT_CERTIFICATE.getId())).thenReturn(Optional.of(FIRST_TEST_GIFT_CERTIFICATE));
        boolean result = giftCertificateService.update(UPDATED_GIFT_CERTIFICATE);
        assertTrue(result);
    }

    @Test
    void deleteById() {
        when(giftCertificateDao.deleteById(TEST_ID)).thenReturn(true);
        boolean result = giftCertificateService.deleteById(TEST_ID);
        assertTrue(result);
    }

    @Test
    void deleteByIdShouldThrowEntityNotFoundException() {
        when(giftCertificateDao.deleteById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.deleteById(NOT_EXIST_ID));
    }
}
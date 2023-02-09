package com.epam.esm.service.impl;

import com.epam.esm.repository.impl.GiftCertificateRepositoryImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.epam.esm.service.impl.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @Mock
    private GiftCertificateRepositoryImpl giftCertificateDao;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;


    @Test
    void getAll() {
        List<GiftCertificate> expected = Arrays.asList(FIRST_TEST_GIFT_CERTIFICATE, SECOND_TEST_GIFT_CERTIFICATE, THIRD_TEST_GIFT_CERTIFICATE);
        when(giftCertificateDao.getAll(PAGE)).thenReturn(expected);
        List<GiftCertificate> actual = giftCertificateService.getAll(PAGE_NUM, PAGE_SIZE);

        assertEquals(actual, actual);
    }

    @Test
    void getGiftCertificatesByParameters() {
        List<GiftCertificate> expected = List.of(FIRST_TEST_GIFT_CERTIFICATE);
        QueryParameters parameters = new QueryParameters("1", null, List.of("1"), null, null);
        when(giftCertificateDao.getGiftCertificatesByParameters(parameters, PAGE)).thenReturn(expected);
        List<GiftCertificate> actual = giftCertificateService.getGiftCertificatesByParameters(parameters, PAGE_NUM, PAGE_SIZE);

        assertEquals(expected, actual);
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
        when(giftCertificateDao.create(GIFT_CERTIFICATE_TO_CREATE)).thenReturn(GIFT_CERTIFICATE_TO_CREATE);
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
    void createShouldThrowDuplicateEntityException() {
        when(giftCertificateDao.getByColumn("name", FIRST_TEST_GIFT_CERTIFICATE.getName())).thenReturn(Optional.of(FIRST_TEST_GIFT_CERTIFICATE));
        assertThrows(DuplicateEntityException.class, () -> giftCertificateService.create(FIRST_TEST_GIFT_CERTIFICATE));
    }

    @Test
    void update() {
        when(giftCertificateDao.update(UPDATED_GIFT_CERTIFICATE)).thenReturn(UPDATED_GIFT_CERTIFICATE);
        when(giftCertificateDao.getById(UPDATED_GIFT_CERTIFICATE.getId())).thenReturn(Optional.of(FIRST_TEST_GIFT_CERTIFICATE));
        GiftCertificate actual = giftCertificateService.update(UPDATED_GIFT_CERTIFICATE);
        assertEquals(UPDATED_GIFT_CERTIFICATE, actual);
    }

    @Test
    void updateShouldThrowInvalidDataException() {
        when(giftCertificateDao.getById(INVALID_GIFT_CERTIFICATE.getId())).thenReturn(Optional.of(INVALID_GIFT_CERTIFICATE));
        when(giftCertificateDao.update(INVALID_GIFT_CERTIFICATE)).thenThrow(new InvalidDataException());
        assertThrows(InvalidDataException.class,()-> giftCertificateService.update(UPDATED_GIFT_CERTIFICATE));
    }



    @Test
    void deleteByIdShouldThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.deleteById(NOT_EXIST_ID));
    }
}
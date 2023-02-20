package com.epam.esm.repository.impl.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;

public class Constants {


    public final static int TEST_ID = 1;

    public final static String TEST_NAME = "1";

    public final static GiftCertificate FIRST_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(1, "1", "1", new BigDecimal(1),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);
    public final static GiftCertificate SECOND_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(2, "2", "2", new BigDecimal(2),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 2);

    public final static GiftCertificate THIRD_TEST_GIFT_CERTIFICATE =
            new GiftCertificate(3, "3", "3", new BigDecimal(3),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 3);
    public final static GiftCertificate GIFT_CERTIFICATE_TO_CREATE = new GiftCertificate(
            1, "certificate new", "description new", new BigDecimal("1.10"),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
            Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")), 1);

/*
    public final static User FIRST_TEST_USER =
            new User(1, "user1");
*/

/*    public final static Order FIRST_TEST_ORDER =
            new Order(1, new BigDecimal(1),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    FIRST_TEST_GIFT_CERTIFICATE, FIRST_TEST_USER);

    public final static Order SECOND_TEST_ORDER =
            new Order(1, new BigDecimal(2),
                    Timestamp.valueOf("2023-01-04 12:07:19").toLocalDateTime().atZone(ZoneId.of("Europe/Kiev")),
                    SECOND_TEST_GIFT_CERTIFICATE, FIRST_TEST_USER);*/

    public final static Tag FIRST_TEST_TAG =
            new Tag(1, "1");

    public final static Pageable PAGE = PageRequest.of(0, 25);

}

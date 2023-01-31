package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;


    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, GiftCertificateDao giftCertificateDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.giftCertificateDao = giftCertificateDao;
    }

    @Override
    public List<Order> getAllByUserId(long userId, int page, int size) {
        userDao.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));

        Pageable pageRequest = PageRequest.of(page, size);

        return orderDao.getAllByUserId(userId, pageRequest);
    }

    @Override
    @Transactional
    public Order create(long userId, long certificateId) {
        Order order = new Order();
        User user = userDao.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
        order.setUser(user);

        GiftCertificate giftCertificate = giftCertificateDao.getById(certificateId)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));
        order.setGiftCertificate(giftCertificate);
        order.setCost(giftCertificate.getPrice());
        return orderDao.create(order);
    }

    @Override
    public Order getById(long orderId) {
        return orderDao.getById(orderId).orElseThrow(()->new EntityNotFoundException("order.notfoundById"));
    }
}

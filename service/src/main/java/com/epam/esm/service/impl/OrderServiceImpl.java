package com.epam.esm.service.impl;

import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.repository.UserRepository;
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

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final GiftCertificateRepository giftCertificateRepository;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, GiftCertificateRepository giftCertificateRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    @Override
    public List<Order> getAllByUserId(long userId, int page, int size) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));

        Pageable pageRequest = PageRequest.of(page, size);

        return orderRepository.getAllByUserId(userId, pageRequest);
    }

    @Override
    @Transactional
    public Order create(long userId, long certificateId) {
        Order order = new Order();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
        order.setUser(user);

        GiftCertificate giftCertificate = giftCertificateRepository.findById(certificateId)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));
        order.setGiftCertificate(giftCertificate);
        order.setCost(giftCertificate.getPrice());
        return orderRepository.save(order);
    }

    @Override
    public Order getById(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()->new EntityNotFoundException("order.notfoundById"));
    }
}

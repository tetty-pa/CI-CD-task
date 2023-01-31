package com.epam.esm.web.link;

import com.epam.esm.entity.Order;
import com.epam.esm.web.controller.GiftCertificatesController;
import com.epam.esm.web.controller.OrdersController;
import com.epam.esm.web.controller.TagsController;
import com.epam.esm.web.controller.UsersController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrdersLinkAdder implements LinkAdder<Order> {
    private final GiftCertificateLinkAdder giftCertificateLinkAdder;
    private final UserLinkAdder userLinkAdder;

    public OrdersLinkAdder(GiftCertificateLinkAdder giftCertificateLinkAdder, UserLinkAdder userLinkAdder) {
        this.giftCertificateLinkAdder = giftCertificateLinkAdder;
        this.userLinkAdder = userLinkAdder;
    }

    @Override
    public void addLinks(Order entity) {
        entity.add(linkTo(methodOn(OrdersController.class).getById(entity.getId())).withSelfRel());
        entity.add(linkTo(methodOn(OrdersController.class).create(entity.getUser().getId(), entity.getGiftCertificate().getId())).withRel("create"));
        if (entity.getGiftCertificate().getLinks().isEmpty()) {
            giftCertificateLinkAdder.addLinks(entity.getGiftCertificate());
        }
        if (entity.getUser().getLinks().isEmpty()) {
            userLinkAdder.addLinks(entity.getUser());
        }
    }
}

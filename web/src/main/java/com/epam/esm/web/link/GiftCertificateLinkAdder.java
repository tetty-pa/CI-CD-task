package com.epam.esm.web.link;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.web.controller.GiftCertificatesController;
import com.epam.esm.web.controller.TagsController;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateLinkAdder {

    public void addLinks(GiftCertificate entity) {
        entity.add(linkTo(methodOn(GiftCertificatesController.class).getById(entity.getId())).withSelfRel());
        entity.add(linkTo(methodOn(GiftCertificatesController.class).create(entity, null)).withRel("create"));
        entity.add(linkTo(GiftCertificatesController.class).slash(entity.getId()).withRel("update"));
        entity.add(linkTo(GiftCertificatesController.class).slash(entity.getId()).withRel("delete"));
        entity.getTagList().stream()
                .filter(t -> t.getLinks().isEmpty())
                .forEach(tag-> tag.add(linkTo(methodOn(TagsController.class).getById(tag.getId())).withSelfRel()));
    }
}

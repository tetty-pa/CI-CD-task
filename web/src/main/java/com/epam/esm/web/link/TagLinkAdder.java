package com.epam.esm.web.link;

import com.epam.esm.model.entity.Tag;
import com.epam.esm.web.controller.TagsController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TagLinkAdder {
    public void addLinks(Tag entity) {
        entity.add(linkTo(methodOn(TagsController.class).getById(entity.getId())).withSelfRel());
        entity.add(linkTo(methodOn(TagsController.class).create(entity, null)).withRel("create"));
        entity.add(linkTo(TagsController.class).slash(entity.getId()).withRel("delete"));
    }
}

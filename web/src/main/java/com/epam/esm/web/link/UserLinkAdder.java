package com.epam.esm.web.link;

import com.epam.esm.entity.User;
import com.epam.esm.web.controller.UsersController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserLinkAdder {
    public void addLinks(User entity) {
        entity.add(linkTo(methodOn(UsersController.class).getById(entity.getId())).withSelfRel());
    }
}

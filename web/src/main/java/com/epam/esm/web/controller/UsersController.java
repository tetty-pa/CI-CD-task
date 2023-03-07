package com.epam.esm.web.controller;

import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.model.entity.User;
import com.epam.esm.service.UserService;
import com.epam.esm.web.link.UserLinkAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    private final UserService userService;
    private final UserLinkAdder linkAdder;

    @Autowired
    public UsersController(UserService userService, UserLinkAdder linkAdder) {
        this.userService = userService;
        this.linkAdder = linkAdder;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "25", required = false) int size) {

        List<User> users = userService.getAll(page, size);
        users.forEach(linkAdder::addLinks);
        return users;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable long id) {
        User user = userService.getById(id);
        linkAdder.addLinks(user);
        return user;
    }
}

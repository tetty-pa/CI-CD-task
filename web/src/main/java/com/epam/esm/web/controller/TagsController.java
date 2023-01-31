package com.epam.esm.web.controller;


import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.service.TagService;
import com.epam.esm.web.link.TagLinkAdder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(path = "/tags")
public class TagsController {
    private final TagService tagService;
    private final TagLinkAdder linkAdder;

    @Autowired
    public TagsController(TagService tagService, TagLinkAdder linkAdder) {
        this.tagService = tagService;
        this.linkAdder = linkAdder;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "25", required = false) int size) {

        List<Tag> all = tagService.getAll(page, size);
        all.forEach(linkAdder::addLinks);
        return all;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody @Valid Tag tag,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }

        tagService.create(tag);
        linkAdder.addLinks(tag);
        return tag;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag getById(@PathVariable("id") long id) {
        Tag tag = tagService.getById(id);
        linkAdder.addLinks(tag);
        return tag;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        tagService.deleteById(id);
    }

    @GetMapping("/users/{userId}")
    public Tag getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(@PathVariable long userId) {
        Tag tag = tagService.getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(userId);
        linkAdder.addLinks(tag);
        return tag;
    }
}
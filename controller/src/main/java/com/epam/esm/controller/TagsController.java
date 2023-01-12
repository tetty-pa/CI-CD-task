package com.epam.esm.controller;

import com.epam.esm.service.TagService;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidDataException;
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

    @Autowired
    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> getAll() {
        return tagService.getAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody @Valid Tag tag,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }
        return tagService.create(tag);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag getById(@PathVariable("id") long id) {
        return tagService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        tagService.deleteById(id);
    }
}

package com.epam.esm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gift-certificates")
public class GiftCertificatesController {

    @GetMapping
    public void getAll() {

    }

    @GetMapping("/{id}")
    public void getById(@PathVariable String id) {

    }
}

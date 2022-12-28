package com.epam.esm.controller;

/*import com.epam.esm.GiftCertificatesService;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;*/
import com.epam.esm.GiftCertificatesService;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    GiftCertificatesService giftCertificatesService;

    @Autowired
    public HelloController(GiftCertificatesService giftCertificatesService) {
        this.giftCertificatesService = giftCertificatesService;
    }

    @GetMapping("/hello")
    public List<GiftCertificate> say() {
        return giftCertificatesService.getAll();
    }

    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world";
    }

}

package com.epam.esm.controller;

import com.epam.esm.entity.util.SortType;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.InvalidDataException;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;


@RestController
@RequestMapping("/gift-certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificate> getAll() {
        return giftCertificateService.getAll();
    }

    @GetMapping("/{id}")
    public GiftCertificate getById(@PathVariable long id) {
        return giftCertificateService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate create(@RequestBody @Valid GiftCertificate giftCertificate,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }
        return giftCertificateService.create(giftCertificate);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable("id") long id) {
        return giftCertificateService.deleteById(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean update(@RequestBody @Valid GiftCertificate giftCertificate,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }
        return giftCertificateService.update(giftCertificate);
    }

    @GetMapping("/params")
    public List<GiftCertificate> getGiftCertificatesByParameters
            (@RequestParam(name = "tag_name", required = false) String tagName,
             @RequestParam(name = "part_of_name", required = false) String partOfName,
             @RequestParam(name = "part_of_description", required = false) String partOfDescription,
             @RequestParam(name = "sort_by_name", required = false)  String sortNameParam,
             @RequestParam(name = "sort_by_created_date", required = false)  String sortDateParam) {

        SortType sortTypeName = null;
        if (!StringUtils.isEmpty(sortNameParam))
            sortTypeName = SortType.valueOf(sortNameParam.toUpperCase(Locale.ROOT));
        SortType sortTypeDescription = null;
        if (!StringUtils.isEmpty(sortDateParam))
            sortTypeDescription = SortType.valueOf(sortDateParam.toUpperCase(Locale.ROOT));

        return giftCertificateService.getGiftCertificatesByParameters(new QueryParameters(partOfName,
                partOfDescription,
                tagName, sortTypeName, sortTypeDescription));
    }
}

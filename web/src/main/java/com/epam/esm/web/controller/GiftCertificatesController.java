package com.epam.esm.web.controller;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.util.QueryParameters;
import com.epam.esm.model.entity.util.SortType;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.web.link.GiftCertificateLinkAdder;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/gift-certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateLinkAdder linkAdder;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService, GiftCertificateLinkAdder linkAdder) {
        this.giftCertificateService = giftCertificateService;
        this.linkAdder = linkAdder;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificate> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "25", required = false) int size) {

        List<GiftCertificate> certificates = giftCertificateService.getAll(page, size);
        certificates.forEach(linkAdder::addLinks);
        return certificates;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate getById(@PathVariable long id) {

        GiftCertificate giftCertificate = giftCertificateService.getById(id);
        linkAdder.addLinks(giftCertificate);
        return giftCertificate;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate create(@RequestBody @Valid GiftCertificate giftCertificate,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }
        giftCertificateService.create(giftCertificate);
        linkAdder.addLinks(giftCertificate);
        return giftCertificate;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        giftCertificateService.deleteById(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate update(@RequestBody @Valid GiftCertificate giftCertificate,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }
        giftCertificateService.update(giftCertificate);
        linkAdder.addLinks(giftCertificate);
        return giftCertificate;
    }


    @GetMapping("/params")
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificate> getGiftCertificatesByParameters
            (@RequestParam(name = "tag_name", required = false) List<String> tagNames,
             @RequestParam(name = "part_of_name", required = false) String partOfName,
             @RequestParam(name = "part_of_description", required = false) String partOfDescription,
             @RequestParam(name = "sort_by_name", required = false) String sortNameParam,
             @RequestParam(name = "sort_by_created_date", required = false) String sortDateParam,
             @RequestParam(value = "page", defaultValue = "0", required = false) int page,
             @RequestParam(value = "size", defaultValue = "25", required = false) int size)  {

        SortType sortTypeName = null;
        if (!StringUtils.isEmpty(sortNameParam))
            sortTypeName = SortType.findSortType(sortNameParam);
        SortType sortTypeDate = null;
        if (!StringUtils.isEmpty(sortDateParam))
            sortTypeDate = SortType.findSortType(sortDateParam);

        List<GiftCertificate> giftCertificatesByParameters = giftCertificateService.
                getGiftCertificatesByParameters(new QueryParameters(partOfName, partOfDescription,
                tagNames, sortTypeName, sortTypeDate), page, size);


        giftCertificatesByParameters.forEach(linkAdder::addLinks);
        return giftCertificatesByParameters;
    }
}

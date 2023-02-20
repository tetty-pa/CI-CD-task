package com.epam.esm.service.impl;

import com.epam.esm.entity.util.SortType;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftCertificateRepository;
    private final TagRepository tagRepository;

    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository, TagRepository tagDao) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagDao;
    }

    @Override
    public List<GiftCertificate> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return giftCertificateRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByParameters(QueryParameters queryParameters, int page, int size) {

        List<Tag> tagList = getTags(queryParameters);

        Sort sort = getSort(queryParameters);

        Pageable pageRequest;
        if (sort != null) {
            pageRequest = PageRequest.of(page, size, sort);
        } else
            pageRequest = PageRequest.of(page, size);

        String partOfName = getPartParameter(queryParameters.getPartOfName());

        String partOfDescription = getPartParameter(queryParameters.getPartOfDescription());

        return giftCertificateRepository.
                getGiftCertificatesByNameLikeAndDescriptionLikeAndTagListIn
                        (partOfName, partOfDescription, tagList, pageRequest);

    }

    private List<Tag> getTags(QueryParameters queryParameters) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : queryParameters.getTagNames()) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseThrow(() -> new EntityNotFoundException("tag.notfoundByName"));
            tagList.add(tag);
        }
        return tagList;
    }

    private Sort getSort(QueryParameters queryParameters) {
        Sort sort = null;
        SortType sortDateParam = queryParameters.getSortDateParam();
        if (sortDateParam != null) {
            sort = Sort.by(Sort.Order.asc("createDate"));
            if (sortDateParam.equals(SortType.DESC))
                sort = Sort.by(Sort.Order.desc("createDate"));
        }

        SortType sortNameParam = queryParameters.getSortNameParam();
        if (sortNameParam != null) {
            if (sort != null) {
                sort = sort.and(Sort.by(Sort.Order.asc("name")));
            } else
                sort = Sort.by(Sort.Order.asc("name"));

            if (sortNameParam.equals(SortType.DESC))
                sort = Sort.by(Sort.Order.desc("name"));

        }
        return sort;
    }

    private String getPartParameter(String partOfParameter) {
        if (partOfParameter == null) partOfParameter = "%%";
        else partOfParameter = "%" + partOfParameter + "%";
        return partOfParameter;
    }



    @Override
    public GiftCertificate getById(long id) {
        return giftCertificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));
    }

    @Override
    @Transactional
    public GiftCertificate create(GiftCertificate giftCertificate) {
        boolean isCertificateExist = giftCertificateRepository.findByName(giftCertificate.getName()).isPresent();
        if (isCertificateExist) {
            throw new DuplicateEntityException("gift-certificate.already.exist");
        }
        List<Tag> tagsToPersist = new ArrayList<>();
        if (giftCertificate.getTagList() != null) {
            for (Tag tag : giftCertificate.getTagList()) {
                Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
                if (tagOptional.isEmpty() || tag.getId() != tagOptional.get().getId()) {
                    throw new EntityNotFoundException("tag.incorrectName");
                }
                tagsToPersist.add(tag);
            }
        }

        giftCertificate.setCreateDate(LocalDateTime.now().atZone(ZoneId.of("Europe/Kiev")));
        giftCertificate.setLastUpdatedDate(LocalDateTime.now().atZone(ZoneId.of("Europe/Kiev")));
        giftCertificate.setTagList(tagsToPersist);
        return giftCertificateRepository.save(giftCertificate);

    }


    @Override
    @Transactional
    public GiftCertificate update(GiftCertificate updatedGiftCertificate) {
        long id = updatedGiftCertificate.getId();
        GiftCertificate giftCertificate = giftCertificateRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));

        findUpdatedFields(giftCertificate, updatedGiftCertificate);
        List<Tag> tagList = updatedGiftCertificate.getTagList();
        if (tagList != null) {
            giftCertificate.setTagList(updateTags(tagList));
        }
        giftCertificate.setLastUpdatedDate(LocalDateTime.now().atZone(ZoneId.of("Europe/Kiev")));
        return giftCertificateRepository.save(giftCertificate);

    }


    private List<Tag> updateTags(List<Tag> tags) {
        List<Tag> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
            if (tagOptional.isEmpty() || tag.getId() != tagOptional.get().getId())
                throw new EntityNotFoundException("tag.incorrectName");
            Tag fullTag = tagOptional.get();
            tagList.add(fullTag);
        }
        return tagList;
    }

    private void findUpdatedFields(GiftCertificate giftCertificate, GiftCertificate updatedGiftCertificate) {
        String name = giftCertificate.getName();
        String updatedName = updatedGiftCertificate.getName();
        if (updatedName != null && !Objects.equals(name, updatedName)) {
            giftCertificate.setName(updatedName);
        }
        String description = giftCertificate.getDescription();
        String updatedDescription = updatedGiftCertificate.getDescription();
        if (updatedDescription != null && !Objects.equals(description, updatedDescription)) {
            giftCertificate.setDescription(updatedDescription);
        }
        BigDecimal price = giftCertificate.getPrice();
        BigDecimal updatedPrice = updatedGiftCertificate.getPrice();
        if (updatedPrice != null && !Objects.equals(updatedPrice, price)) {
            giftCertificate.setPrice(updatedPrice);
        }
        int duration = giftCertificate.getDuration();
        int updatedDuration = updatedGiftCertificate.getDuration();

        if (updatedDuration != 0 && !Objects.equals(updatedDuration, duration)) {
            giftCertificate.setDuration(updatedDuration);
        }

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        giftCertificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));
        giftCertificateRepository.deleteById(id);
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDAO;
    private final TagDao tagDao;

    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDAO, TagDao tagDao) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDao = tagDao;
    }

    @Override
    public List<GiftCertificate> getAll() {
        return giftCertificateDAO.getAll();
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByParameters(QueryParameters queryParameters) {
        return giftCertificateDAO.getGiftCertificatesByParameters(queryParameters);
    }

    @Override
    public GiftCertificate getById(long id) {
        return giftCertificateDAO.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundById"));
    }

    @Override
    @Transactional
    public GiftCertificate create(GiftCertificate giftCertificate) {
        boolean isCertificateExist = giftCertificateDAO.getByColumn("name", giftCertificate.getName()).isPresent();
        if (isCertificateExist) {
            throw new DuplicateEntityException("gift-certificate.already.exist");
        }
        giftCertificateDAO.create(giftCertificate);
        String name = giftCertificate.getName();
        long id = giftCertificateDAO.getByColumn("name", name)
                .orElseThrow(() -> new EntityNotFoundException("gift-certificate.notfoundByName")).getId();
        createCertificateTagsWithReference(giftCertificate.getTagList(), id);
        return giftCertificate;
    }

    private void createCertificateTagsWithReference(List<Tag> tags, long certificateId) {
        for (Tag tag : tags) {
            Optional<Tag> tagOptional = tagDao.getById(tag.getId());
            Tag fullTag = tagOptional.orElse(tagDao.create(tag).get());
            long tagId = tagDao.getByColumn("name", fullTag.getName()).get().getId();
            giftCertificateDAO.addTagToGiftCertificate(certificateId, tagId);
        }
    }


    @Override
    @Transactional
    public boolean update(GiftCertificate updatedGiftCertificate) {
        long id = updatedGiftCertificate.getId();
        Optional<GiftCertificate> giftCertificate = giftCertificateDAO.getById(id);
        if (giftCertificate.isEmpty()) {
            throw new EntityNotFoundException("gift-certificate.notfoundById");
        }
        boolean res = giftCertificateDAO.update(id, findUpdatedFields(updatedGiftCertificate, giftCertificate.get()));
        List<Tag> newTags = updatedGiftCertificate.getTagList();
        if (newTags.isEmpty()) return res;
        updateTags(newTags, id);
        return true;
    }



    private void updateTags(List<Tag> tags, long certificateId) {
        for (Tag tag : tags) {
            String tagName = tag.getName();
            Optional<Tag> tagOptional = tagDao.getByColumn("name", tagName);
            Tag fullTag = tagOptional.orElseGet(() -> tagDao.create(tag).get());
            long tagId = giftCertificateDAO.getByColumn("name", fullTag.getName()).get().getId();
            if (!tagDao.getAllByGiftCertificateId(certificateId).contains(tag)) {
                giftCertificateDAO.addTagToGiftCertificate(certificateId, tagId);
            }
        }
    }

    private Map<String, Object> findUpdatedFields(GiftCertificate giftCertificate, GiftCertificate updatedGiftCertificate) {
        Map<String, Object> updatedFields = new HashMap<>();
        String name = giftCertificate.getName();
        String updatedName = updatedGiftCertificate.getName();

        if (!Objects.equals(name, updatedName)) {
            updatedFields.put("name", name);
        }
        String description = giftCertificate.getDescription();
        String updatedDescription = updatedGiftCertificate.getName();

        if (!Objects.equals(description, updatedDescription)) {
            updatedFields.put("description", description);
        }
        BigDecimal price = giftCertificate.getPrice();
        BigDecimal updatedPrice = updatedGiftCertificate.getPrice();
        if (!Objects.equals(price, updatedPrice)) {
            updatedFields.put("price", price);
        }
        int duration = giftCertificate.getDuration();
        int updatedDuration = updatedGiftCertificate.getDuration();
        if (duration != updatedDuration) {
            updatedFields.put("duration", duration);
        }
        return updatedFields;
    }

    @Override
    public boolean deleteById(long id) {
        if (giftCertificateDAO.deleteById(id)) return true;
        throw new EntityNotFoundException("gift-certificate.notfoundById");
    }
}

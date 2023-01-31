package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.service.TagService;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final UserDao userDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao, UserDao userDao) {
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @Override
    public List<Tag> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return tagDao.getAll(pageRequest);
    }

    @Transactional
    @Override
    public Tag create(Tag tag) {
        boolean isTagExist = tagDao.getByColumn("name", tag.getName()).isPresent();
        if (isTagExist) {
            throw new DuplicateEntityException("tag.already.exist");
        }
        return tagDao.create(tag);
    }

    @Override
    public Tag getById(long id) {
        return tagDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById"));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        tagDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById"));
        tagDao.deleteById(id);
    }

    @Override
    public Tag getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(long userId) {
        userDao.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
        return tagDao.getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(userId)
                .orElseThrow(() -> new EntityNotFoundException("order.notfoundById"));
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.service.TagService;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    @Transactional
    @Override
    public Tag create(Tag tag) {
        boolean isTagExist = tagDao.getByColumn("name", tag.getName()).isPresent();
        if (isTagExist) {
            throw new DuplicateEntityException("tag.already.exist");
        }
        return tagDao.create(tag)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById"));

    }

    @Override
    public Tag getById(long id) {
        return tagDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById" ));
    }

    @Override
    public boolean deleteById(long id) {
        if (tagDao.deleteById(id)) return true;
        throw new EntityNotFoundException("tag.notfoundById");

    }
}

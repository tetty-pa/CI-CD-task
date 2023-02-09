package com.epam.esm.service.impl;

import com.epam.esm.repository.UserRepository;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.service.TagService;
import com.epam.esm.repository.TagRepository;
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

    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tag> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return tagRepository.getAll(pageRequest);
    }

    @Transactional
    @Override
    public Tag create(Tag tag) {
        boolean isTagExist = tagRepository.getByColumn("name", tag.getName()).isPresent();
        if (isTagExist) {
            throw new DuplicateEntityException("tag.already.exist");
        }
        return tagRepository.create(tag);
    }

    @Override
    public Tag getById(long id) {
        return tagRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById"));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        tagRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("tag.notfoundById"));
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(long userId) {
        userRepository.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user.notfoundById"));
        return tagRepository.getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(userId)
                .orElseThrow(() -> new EntityNotFoundException("order.notfoundById"));
    }
}

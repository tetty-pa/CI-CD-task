package com.epam.esm.service;


import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * Gets all Tags.
     *
     * @return List of Tags
     */
    List<Tag> getAll();

    /**
     * Creates new Tag.
     *
     * @param tag Tag to create
     * @return Tag
     */
    Tag create(Tag tag);

    /**
     * Gets Tag by id.
     *
     * @param id Tag id to get
     * @return Tag
     */
    Tag getById(long id);

    /**
     * Deletes Gift Certificates.
     *
     * @param id Tag id to delete
     * @return boolean result
     */
    boolean deleteById(long id);
}

package com.epam.esm.service;


import com.epam.esm.model.entity.Tag;

import java.util.List;


public interface TagService {
    /**
     * Gets all Tags.
     *
     * @param page page number for pagination
     * @param size   page size for pagination
     * @return List of Tags
     */
    List<Tag> getAll(int page, int size);

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
     */
    void deleteById(long id);

    /**
     * Gets Most Widely Used Tag Of User With Highest Cost Of All Orders Tag .
     *
     * @param userId User id to get
     * @return Tag
     */
    Tag getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(long userId);

}

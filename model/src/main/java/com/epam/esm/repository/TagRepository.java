package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TagRepository {
    /**
     * Creates new Tag.
     *
     * @param tag Tag to create
     * @return Optional<Tag> Tag if founded or Empty if not
     */
    Tag create(Tag tag);

    /**
     * Gets all Tags.
     *
     * @param pageable object with pagination info(page number, page size)
     * @return List of Tags
     */
    List<Tag> getAll(Pageable pageable);

    /**
     * Gets Tag by column name.
     *
     * @param columnName Tag column name to get
     * @param value      Tag column value to get
     * @return Optional<Tag> Tag if founded or Empty if not
     */
    Optional<Tag> getByColumn(String columnName, String value);

    /**
     * Gets Tag by id.
     *
     * @param id Tag id to get
     * @return Optional<Tag> Tag if founded or Empty if not
     */
    Optional<Tag> getById(long id);

    /**
     * Deletes Tag.
     *
     * @param id Tag id to delete
     */
    void deleteById(long id);


    /**
     * Gets Most Widely Used Tag Of User With Highest Cost Of All Orders Tag .
     *
     * @param userId User id to get
     * @return Optional<Tag>
     */
    Optional<Tag> getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(long userId);

}

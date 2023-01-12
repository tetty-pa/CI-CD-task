package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Tag
 */
public interface TagDao {
    /**
     * Creates new Tag.
     *
     * @param tag Tag to create
     * @return Optional<Tag> Tag if founded or Empty if not
     */
    Optional<Tag> create(Tag tag);

    /**
     * Gets all Tags.
     *
     * @return List of Tags
     */
    List<Tag> getAll();

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
     * @return boolean result
     */
    boolean deleteById(long id);

    /**
     * Gets all Tags by Gift Certificate id.
     *
     * @param id Gift Certificate id
     * @return List of Tags
     */
    List<Tag> getAllByGiftCertificateId(long id);
}

package com.epam.esm.dao;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DAO interface for Gift Certificate
 */
public interface GiftCertificateDao {

    /**
     * Creates new Gift Certificate.
     *
     * @param giftCertificate Gift Certificate to create
     * @return Optional<GiftCertificate> Certificate if founded or Empty if not
     */
    Optional<GiftCertificate> create(GiftCertificate giftCertificate);

    /**
     * Updates    Gift Certificates.
     *
     * @param id                        Gift Certificate id to update
     * @param giftCertificateUpdateInfo information that needs to be updated
     * @return boolean result
     */
    boolean update(long id, Map<String, Object> giftCertificateUpdateInfo);

    /**
     * Adds Tag to Gift Certificates.
     *
     * @param giftCertificateId Gift Certificate id
     * @param tagId             Tag id that needs to be added
     * @return boolean result
     */
    boolean addTagToGiftCertificate(long giftCertificateId, long tagId);

    /**
     * Gets all Gift Certificates.
     *
     * @return List of Gift Certificates
     */
    List<GiftCertificate> getAll();

    /**
     * Gets all Gift Certificates with parameters.
     *
     * @param queryParameters Parameters to search
     * @return List of Gift Certificates
     */
    List<GiftCertificate> getGiftCertificatesByParameters(QueryParameters queryParameters);

    /**
     * Gets Gift Certificate by column name.
     *
     * @param columnName Gift Certificate column name to get
     * @param value      Gift Certificate column value to get
     * @return Optional<GiftCertificate> Certificate if founded or Empty if not
     */
    Optional<GiftCertificate> getByColumn(String columnName, String value);

    /**
     * Gets Gift Certificate by id.
     *
     * @param id Gift Certificate id to get
     * @return Optional<GiftCertificate> Certificate if founded or Empty if not
     */
    Optional<GiftCertificate> getById(long id);

    /**
     * Deletes Gift Certificates.
     *
     * @param id Gift Certificate id to delete
     * @return boolean result
     */
    boolean deleteById(long id);
}

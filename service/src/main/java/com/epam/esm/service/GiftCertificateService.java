package com.epam.esm.service;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.exception.EntityNotFoundException;

import java.util.List;

/**
 * Service interface for Gift Certificate
 */
public interface GiftCertificateService {
    /**
     * Gets all Tags.
     *
     * @return List of all Gift Certificates
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
     * Gets Gift Certificate by id.
     *
     * @param id Gift Certificate id to get
     * @return GiftCertificate
     */
    GiftCertificate getById(long id) throws EntityNotFoundException;

    /**
     * Creates new Gift Certificate.
     *
     * @param giftCertificate Gift Certificate to create
     * @return GiftCertificate
     */
    GiftCertificate create(GiftCertificate giftCertificate);

    /**
     * Updates    Gift Certificates.
     *
     * @param giftCertificate Gift Certificate that needs to be updated
     * @return boolean result
     */
    boolean update(GiftCertificate giftCertificate);

    /**
     * Deletes Gift Certificates.
     *
     * @param id Gift Certificate id to delete
     * @return boolean result
     */
    boolean deleteById(long id);
}

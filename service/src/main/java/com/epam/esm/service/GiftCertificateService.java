package com.epam.esm.service;


import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.util.QueryParameters;
import com.epam.esm.exception.EntityNotFoundException;

import java.util.List;

/**
 * Service interface for Gift Certificate
 */
public interface GiftCertificateService {
    /**
     * Gets all Tags.
     *
     * @param page   page number for pagination
     * @param size   page size for pagination
     * @return List of all Gift Certificates
     */
    List<GiftCertificate> getAll(int page, int size);

    /**
     * Gets all Gift Certificates with parameters.
     *
     * @param queryParameters Parameters to search
     * @param page   page number for pagination
     * @param size   page size for pagination
     * @return List of Gift Certificates
     */
    List<GiftCertificate> getGiftCertificatesByParameters(QueryParameters queryParameters, int page, int size);

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
     * @return created GiftCertificate
     */
    GiftCertificate create(GiftCertificate giftCertificate);

    /**
     * Updates    Gift Certificates.
     *
     * @param giftCertificate Gift Certificate that needs to be updated
     * @return updated GiftCertificate
     */
    GiftCertificate update(GiftCertificate giftCertificate);

    /**
     * Deletes Gift Certificates.
     *
     * @param id Gift Certificate id to delete
     */
    void deleteById(long id);
}

package com.epam.esm.dao;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface GiftCertificateDao {

    /**
     * Creates new Gift Certificate.
     *
     * @param giftCertificate Gift Certificate to create
     * @return GiftCertificate Certificate if founded or Empty if not
     */
    GiftCertificate create(GiftCertificate giftCertificate);

    /**
     * Updates    Gift Certificates.
     *
     * @param giftCertificate Gift Certificate to update
     * @return GiftCertificate updated giftCertificate
     */
    GiftCertificate update(GiftCertificate giftCertificate);


    /**
     * Gets all Gift Certificates.
     *
     * @param pageable object with pagination info(page number, page size)
     * @return List of Gift Certificates
     */
    List<GiftCertificate> getAll(Pageable pageable);

    /**
     * Gets all Gift Certificates with parameters.
     *
     * @param pageable object with pagination info(page number, page size)
     * @param queryParameters Parameters to search
     * @return List of Gift Certificates
     */
    List<GiftCertificate> getGiftCertificatesByParameters(QueryParameters queryParameters, Pageable pageable);

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
     */
    void deleteById(long id);
}

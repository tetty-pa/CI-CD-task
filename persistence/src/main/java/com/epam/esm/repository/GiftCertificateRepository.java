package com.epam.esm.repository;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.util.QueryParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface GiftCertificateRepository extends JpaRepository<GiftCertificate, Long> {


    /**
     * Gets all Gift Certificates with parameters.
     *
     * @param pageable object with pagination info(page number, page size)
     * @param tagList  Parameters to search
     * @return List of Gift Certificates
     */

    List<GiftCertificate> getGiftCertificatesByNameLikeAndDescriptionLikeAndTagListIn
    ( String name, String description, List<Tag> tagList, Pageable pageable);

    /**
     * Gets Gift Certificate by column name.
     *
     * @param name Gift Certificate column name to get
     * @return Optional<GiftCertificate> Certificate if founded or Empty if not
     */
    Optional<GiftCertificate> findByName(String name);


}

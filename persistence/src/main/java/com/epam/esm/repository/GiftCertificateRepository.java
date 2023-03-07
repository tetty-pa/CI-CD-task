package com.epam.esm.repository;


import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GiftCertificateRepository extends JpaRepository<GiftCertificate, Long> {


    /**
     * Gets all Gift Certificates with parameters.
     *
     * @param name Gift Certificate`s name to find
     * @param description Gift Certificate`s description to find
     * @param pageable object with pagination info(page number, page size)
     * @param tagList Gift Certificate`s list of tags to find
     * @return List of Gift Certificates
     */

    List<GiftCertificate> getGiftCertificatesByNameLikeAndDescriptionLikeAndTagListIn
    (String name, String description, List<Tag> tagList, Pageable pageable);

    /**
     * Gets Gift Certificate by  name.
     *
     * @param name Gift Certificate  name to get
     * @return Optional<GiftCertificate> Certificate if founded or Empty if not
     */
    Optional<GiftCertificate> findByName(String name);


}

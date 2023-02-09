package com.epam.esm.repository.impl;

import com.epam.esm.QueryBuilder;
import com.epam.esm.repository.AbstractRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GiftCertificateRepositoryImpl extends AbstractRepository<GiftCertificate> implements GiftCertificateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public GiftCertificateRepositoryImpl() {
        super(GiftCertificate.class, "gift_certificates");
    }


    @Override
    public List<GiftCertificate> getGiftCertificatesByParameters(
            QueryParameters queryParameters, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<GiftCertificate> criteriaQuery =
                QueryBuilder.buildGetAllByParametersQuery(queryParameters, criteriaBuilder);
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        
    }
}

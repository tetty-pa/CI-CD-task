package com.epam.esm.repository.impl;

import org.springframework.stereotype.Repository;

@Repository
public class GiftCertificateDaoImpl  {

    /*@PersistenceContext
    private EntityManager entityManager;

    public GiftCertificateDaoImpl() {
        super(GiftCertificate.class, "gift_certificates");
    }*/


    /*@Override
    public List<GiftCertificate> getGiftCertificatesByParameters(
            QueryParameters queryParameters, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<GiftCertificate> criteriaQuery =
                QueryBuilder.buildGetAllByParametersQuery(queryParameters, criteriaBuilder);
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        
    }*/
}

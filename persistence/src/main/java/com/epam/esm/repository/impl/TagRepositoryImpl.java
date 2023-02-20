package com.epam.esm.repository.impl;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class TagRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;




    public Optional<Tag> getMostWidelyUsedTagOfUserWithHighestCostOfAllOrders(long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Order> root = cq.from(Order.class);


        Join<Tag, Order> tagOrderJoin = root.join("giftCertificate")
                .join("tagList");

        cq.select(root.get("giftCertificate").get("tagList"))
                .where(cb.equal(root.get("user").get("id"), userId))
                .groupBy(tagOrderJoin.get("id"))
                .orderBy(cb.desc(cb.count(tagOrderJoin.get("id"))),
                        cb.desc(cb.sum(root.get("cost"))));


        return entityManager.createQuery(cq).getResultStream().findFirst();
    }

}

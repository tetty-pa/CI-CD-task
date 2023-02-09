package com.epam.esm.repository.impl;

import com.epam.esm.repository.AbstractRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class TagRepositoryImpl extends AbstractRepository<Tag> implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagRepositoryImpl() {
        super(Tag.class, "tags");
    }



    @Override
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

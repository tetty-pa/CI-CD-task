package com.epam.esm.repository.impl;

import com.epam.esm.repository.AbstractRepository;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderRepositoryImpl extends AbstractRepository<Order> implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public OrderRepositoryImpl() {
        super(Order.class, "orders");
    }

    @Override
    public List<Order> getAllByUserId(long userId, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root);

        Join<User, Order> userJoin = root.join("user");
        Predicate joinIdPredicate = cb.equal(userJoin.get("id"), userId);
        cq.where(joinIdPredicate);

        return entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

}

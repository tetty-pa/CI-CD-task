package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    public OrderDaoImpl() {
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

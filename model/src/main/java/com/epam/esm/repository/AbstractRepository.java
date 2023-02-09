package com.epam.esm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private final Class<T> entityType;

    public AbstractRepository(Class<T> entityType, String tableName) {
        this.entityType = entityType;

    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<T> getAll(Pageable pageable) {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public Optional<T> getByColumn(String columnName, String value) {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() +
                        " e WHERE e." + columnName + " = :columnName", entityType)
                .setParameter("columnName", value)
                .getResultList().stream()
                .findFirst();

    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }


    public Optional<T> getById(long id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }



    public void deleteById(long id) {
        T entity = entityManager.find(entityType, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }


}

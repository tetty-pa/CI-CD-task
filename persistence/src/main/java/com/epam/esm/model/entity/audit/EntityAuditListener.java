package com.epam.esm.model.entity.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


@Component
public class EntityAuditListener {
    private final Logger LOGGER = LoggerFactory.getLogger(EntityAuditListener.class);

    @PrePersist
    private void onPrePersist(Object object) {
        LOGGER.info( "persist object: " + object);
    }

    @PreUpdate
    private void onPreUpdate(Object object) {
        LOGGER.info( "update object: " + object);
    }

    @PreRemove
    public void onPreRemove(Object object) {
        LOGGER.info( "remove object: " + object);
    }

}

package com.epam.esm.entity.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


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

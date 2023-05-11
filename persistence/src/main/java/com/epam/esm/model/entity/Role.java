package com.epam.esm.model.entity;

import com.epam.esm.model.entity.audit.EntityAuditListener;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@EntityListeners(EntityAuditListener.class)
public class Role extends AbstractEntity{

    public enum RoleType {
        GUEST, USER, ADMIN
    }

    public static RoleType getRole(long  roleId) {
        return RoleType.values()[(int)roleId];
    }

    @Column(name = "name", length = 60, unique = true)
    private String name;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

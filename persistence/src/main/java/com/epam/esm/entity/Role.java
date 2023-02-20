package com.epam.esm.entity;

import com.epam.esm.entity.audit.EntityAuditListener;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
@EntityListeners(EntityAuditListener.class)
public class Role extends AbstractEntity{

    @Column(name = "name", length = 60, unique = true)
    private String name;


/*    @OneToOne(mappedBy = "role_id", fetch = FetchType.LAZY)
    private List<User> users;*/

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

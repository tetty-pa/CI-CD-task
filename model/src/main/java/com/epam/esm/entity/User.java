package com.epam.esm.entity;

import com.epam.esm.entity.audit.EntityAuditListener;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(EntityAuditListener.class)
public class User extends AbstractEntity{

    @Column(length = 80, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private final List<Order> orders = new ArrayList<>();

    public User(long id, String name) {

        this.name = name;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User tag = (User) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name);
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                '}';
    }
}

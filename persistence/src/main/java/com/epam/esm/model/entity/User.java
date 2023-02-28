package com.epam.esm.model.entity;

import com.epam.esm.model.entity.audit.EntityAuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(EntityAuditListener.class)
public class User extends AbstractEntity {

    @Column(length = 80, nullable = false)
    @Size(min = 1, max = 80, message = "tag.invalidName")
    private String name;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private final List<Order> orders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {
    }


    public User(String name, String email, String password, Role roleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Role getRoleId() {
        return role;
    }

    public void setRoleId(Role roleId) {
        this.role = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(orders, user.orders) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, password, orders, role);
    }
}

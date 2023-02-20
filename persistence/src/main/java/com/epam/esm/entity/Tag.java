package com.epam.esm.entity;

import com.epam.esm.entity.audit.EntityAuditListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tags")
@EntityListeners(EntityAuditListener.class)
public class Tag extends AbstractEntity{

    @Size(min = 1, max = 80, message = "tag.invalidName")
    private String name;

    public Tag(long id, String name) {
        this.name = name;
    }

    public Tag() {}

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
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name);
    }

    @Override
    public String toString() {
        return "Tag{" +
                ", name='" + name + '\'' +
                '}';
    }
}

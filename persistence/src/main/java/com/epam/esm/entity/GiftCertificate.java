package com.epam.esm.entity;

import com.epam.esm.entity.audit.EntityAuditListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gift_certificates")
@EntityListeners(EntityAuditListener.class)
public class GiftCertificate extends AbstractEntity {

    @Size(min = 1, max = 80, message = "gift-certificate.invalidName")
    @Column(length = 80, nullable = false)
    private String name;
    @Size(min = 1, max = 250, message = "gift-certificate.invalidDescription")
    @Column(length = 250, nullable = false)
    private String description;
    @Min(value = 1, message = "gift-certificate.invalidPrice")
    @Column(nullable = false)
    private BigDecimal price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "create_date", nullable = false, updatable = false)
    private ZonedDateTime createDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "last_updated_date", nullable = false)
    private ZonedDateTime lastUpdatedDate;
    @Min(value = 1, message = "gift-certificate.invalidDuration")
    @Column(nullable = false)
    private int duration;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "gift_certificate_has_tag",
            joinColumns = @JoinColumn(name = "gift_certificate_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "ID")
    )
    List<Tag> tagList = new ArrayList<>();

    public GiftCertificate() {
    }

    public GiftCertificate(long id, String name, String description, BigDecimal price, ZonedDateTime createDate, ZonedDateTime lastUpdatedDate, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.duration = duration;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(ZonedDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return duration == that.duration && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, createDate, lastUpdatedDate, duration);
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", duration=" + duration +
                ", tagList=" + tagList +
                '}';
    }
}

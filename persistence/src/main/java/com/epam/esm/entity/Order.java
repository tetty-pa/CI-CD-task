package com.epam.esm.entity;

import com.epam.esm.entity.audit.EntityAuditListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
@EntityListeners(EntityAuditListener.class)
public class Order extends AbstractEntity{

    @Column( nullable = false)
    private BigDecimal cost;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Column(name = "order_date", nullable = false, updatable = false)
    private ZonedDateTime orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "gift_certificate_id"
    )
    private GiftCertificate giftCertificate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id"
    )
    private User user;


    public Order() {

    }

    public Order(int id, BigDecimal cost, ZonedDateTime orderDate, GiftCertificate giftCertificate, User user) {
        this.cost = cost;
        this.orderDate = orderDate;
        this.giftCertificate = giftCertificate;
        this.user = user;
    }

    @PrePersist
    public void onCreate() {
        orderDate = ZonedDateTime.now();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return   Objects.equals(cost, order.cost) && Objects.equals(orderDate, order.orderDate);
    }



    @Override
    public int hashCode() {
        return Objects.hash(  cost, orderDate);
    }
}

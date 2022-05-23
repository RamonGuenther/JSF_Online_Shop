package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

//Order
@Entity
public class Ordering implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany(mappedBy = "ordering")
    private List<OrderedProduct> orderedProductList;

    private String orderComment;

    public Ordering(Address billingAddress, Address deliveryAddress, List<OrderedProduct> orderedProductList) {
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderedProductList = orderedProductList;
    }

    public Ordering() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }
}

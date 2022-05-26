package de.fhswf.fit.entities;

import de.fhswf.fit.entities.enums.OrderState;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ordering", cascade = {CascadeType.ALL})
    private List<OrderedProduct> orderedProductList;

    private String orderComment;

    private OrderState orderState;

    public Ordering(Address billingAddress, Address deliveryAddress, String orderComment) {
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderComment = orderComment;
        orderedProductList = new ArrayList<>();
        orderState = OrderState.OFFEN;
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

    public OrderState getOrderState() {
        return orderState;
    }

    public void addOrderedProduct(OrderedProduct orderedProduct) {

        for (OrderedProduct op : orderedProductList) {
            if (op.getProduct().equals(orderedProduct.getProduct())) {
                System.out.println("Gibt es schon");
                op.setAmount(op.getAmount() + 1);
                return;
            }
        }
        System.out.println("Gab es nicht");
        orderedProductList.add(orderedProduct);
    }

    public void editOrderedProduct(OrderedProduct orderedProduct) {
        for (OrderedProduct op : orderedProductList) {
            if (op.getProduct().equals(orderedProduct.getProduct())) {
                System.out.println("Gibt es schon");
                op.setAmount(orderedProduct.getAmount());
                return;
            }
        }
    }

    public void removeOrderedProduct(OrderedProduct orderedProduct) {

        for (int i = 0; i < orderedProductList.size(); i++) {
            if (orderedProductList.get(i).getProduct().getId().equals(orderedProduct.getProduct().getId())) {
                orderedProductList.remove(i);
                System.out.println("GEFUNDEN");
                return;
            }
        }
    }

    public void setOrderStateToOrder() {
        orderState = OrderState.BESTELLT;
    }
}

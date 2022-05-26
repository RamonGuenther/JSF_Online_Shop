package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class OrderedProduct implements Serializable {

    @EmbeddedId
    private OrderedProductKey id;

    @ManyToOne
    @MapsId("orderingId")
    @JoinColumn(name = "ordering_id")
    private Ordering ordering;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;

    public OrderedProduct(Ordering ordering, Product product, int amount) {
        id=new OrderedProductKey(product.getId(),ordering.getId());
        this.ordering = ordering;
        this.product = product;
        this.amount = amount;
    }

    public OrderedProduct() {
    }

    public OrderedProductKey getOrderedProductKey() {
        return id;
    }

    public void setOrderedProductKey(OrderedProductKey orderedProductKey) {
        this.id = orderedProductKey;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override //TODO: EY
    public boolean equals(Object o) {
        return (o instanceof Product) && getProduct().getId().equals(((Product) o).getId());
    }
}

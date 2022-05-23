package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class OrderedProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ordering ordering;

    @ManyToOne
    private Product product;

    private int amount;

    public OrderedProduct(Ordering ordering, Product product, int amount) {
        this.ordering = ordering;
        this.product = product;
        this.amount = amount;
    }

    public OrderedProduct() {
    }

    public Long getId() {
        return id;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Product getProduct() {
        return product;
    }
}

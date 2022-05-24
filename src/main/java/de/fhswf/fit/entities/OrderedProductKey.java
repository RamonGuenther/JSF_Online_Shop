package de.fhswf.fit.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderedProductKey implements Serializable {

    private String productId;

    private Long orderingId;

    public OrderedProductKey(String productId, Long orderingId) {
        this.productId = productId;
        this.orderingId = orderingId;
    }

    public OrderedProductKey() {
    }

    public String getProductId() {
        return productId;
    }

    public Long getOrderingId() {
        return orderingId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrderingId(Long orderId) {
        this.orderingId = orderId;
    }

}

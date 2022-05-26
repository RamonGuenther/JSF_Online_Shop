package de.fhswf.fit.stores;


import de.fhswf.fit.entities.OrderedProduct;
import de.fhswf.fit.entities.OrderedProductKey;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.io.Serializable;

@Stateless
@PersistenceUnit
public class OrderedProductStore implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void delete(OrderedProduct op){
        op =  entityManager.merge(op);
        entityManager.remove(op);
    }

    public OrderedProduct getById(OrderedProductKey id) {
        return entityManager.find(OrderedProduct.class, id);
    }

}

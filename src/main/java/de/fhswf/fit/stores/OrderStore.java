package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Ordering;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.io.Serializable;

@PersistenceUnit
@Stateless
public class OrderStore implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Ordering newOrdering){
        entityManager.persist(newOrdering);
    }
    public void update(Ordering ordering){
        entityManager.merge(ordering);
    }
    public void delete(Ordering ordering) {
        ordering = entityManager.merge(ordering);
        entityManager.remove(ordering);
    }
}

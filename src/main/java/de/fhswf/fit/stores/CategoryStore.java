package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.io.Serializable;

/**
 *
 */
@PersistenceUnit
@Stateless
public class CategoryStore implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Category newCategory){
        entityManager.persist(newCategory);
    }

}

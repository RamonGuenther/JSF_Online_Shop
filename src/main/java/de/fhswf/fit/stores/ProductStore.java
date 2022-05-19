package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

@PersistenceUnit
@Stateless
public class ProductStore implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Product> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void save(Product newProduct){
        entityManager.persist(newProduct);
    }

}

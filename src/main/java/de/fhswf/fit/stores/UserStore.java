package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Product;
import de.fhswf.fit.entities.User;
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
public class UserStore implements Serializable {


    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
    public void save(User newUser){
        entityManager.persist(newUser);
    }

    public void update(User user){
        entityManager.merge(user);
    }
}

package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Benutzer;
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

    public List<Benutzer> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Benutzer> criteriaQuery = criteriaBuilder.createQuery(Benutzer.class);
        Root<Benutzer> root = criteriaQuery.from(Benutzer.class);
        criteriaQuery.select(root);
        TypedQuery<Benutzer> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
    public void save(Benutzer newBenutzer){
        entityManager.persist(newBenutzer);
    }

    public void update(Benutzer benutzer){
        entityManager.merge(benutzer);
    }
}

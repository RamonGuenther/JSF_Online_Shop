package de.fhswf.fit.stores;

import de.fhswf.fit.entities.Address;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.io.Serializable;

@PersistenceUnit
@Stateless
public class AddressStore implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void delete(Address address){
        address =  entityManager.merge(address);
        entityManager.remove(address);
    }

    public void update(Address address){
        entityManager.merge(address);
    }
}

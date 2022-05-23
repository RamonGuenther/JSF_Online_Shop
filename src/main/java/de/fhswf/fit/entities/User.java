package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Address> addressList;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Ordering> orderingList;


    public User(List<Address> addressList) {
        this.addressList = addressList;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Ordering> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }
}

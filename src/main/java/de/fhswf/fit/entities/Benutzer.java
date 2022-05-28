package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Benutzer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "benutzer_id")
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "benutzer_id")
    private List<Ordering> orderingList;

    public Benutzer(String username) {
        this.username = username;
        addressList = new ArrayList<>();
        orderingList = new ArrayList<>();
    }

    public Benutzer() {

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

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addAddress(Address newAddress){
        addressList.add(newAddress);
    }

    public void addOrder(Ordering newOrder){
        orderingList.add(newOrder);
    }
}

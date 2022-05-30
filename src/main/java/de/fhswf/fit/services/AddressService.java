package de.fhswf.fit.services;

import de.fhswf.fit.entities.Address;
import de.fhswf.fit.entities.Benutzer;
import de.fhswf.fit.stores.AddressStore;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("addressService")
public class AddressService implements Serializable {

    private transient UserStore userStore;
    private transient AddressStore addressStore;

    private Benutzer currentUser;

    private List<Address> addressList;

    private Address selectedAddress;

    private Address address;

    public AddressService() {
        currentUser = new Benutzer();
        addressList = new ArrayList<>();
        selectedAddress = new Address();
        address = new Address();
    }

    private void init() {
        currentUser = userStore.getAll().get(0);
        addressList = currentUser.getAddressList();
    }

    @Inject
    public void setStores(UserStore userStore, AddressStore addressStore) {
        System.out.println("Initialisierung des AddressServices");
        this.userStore = userStore;
        this.addressStore = addressStore;
        init();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void deleteAddress(Address address) {
        currentUser.removeAddress(address);
        userStore.update(currentUser);
        addressList = currentUser.getAddressList();
        addressStore.delete(address);
    }

    public Address getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public void updateAddress(Address address) {
        addressStore.update(address);
        addressList = currentUser.getAddressList();
    }

    public void saveAddress(Address address) {
        System.out.println("Save Address");
        currentUser.addAddress(address);
        userStore.update(currentUser);
        System.out.println(currentUser.getAddressList().size());
        this.address = new Address();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package de.fhswf.fit.services;

import de.fhswf.fit.entities.Benutzer;
import de.fhswf.fit.entities.Ordering;
import de.fhswf.fit.entities.enums.OrderState;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Named("orderHistoryService")
public class OrderHistoryService implements Serializable {

    private transient UserStore userStore;

    private List<Ordering> orderingList;

    private Ordering selectedOrdering;


    public OrderHistoryService() {
        orderingList = new ArrayList<>();
        selectedOrdering = new Ordering();
    }

    private void init(){
        Benutzer currentUser = userStore.getById(1L);
        orderingList = currentUser.getOrderingList().stream().filter(e-> e.getOrderState().equals(OrderState.BESTELLT)).collect(Collectors.toList());
    }

    @Inject
    public void setUserStore(UserStore userStore){
        System.out.println("Initialisierung OrderHistoryService");
        this.userStore = userStore;
        init();
    }

    public List<Ordering> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }

    public Ordering getSelectedOrdering() {
        return selectedOrdering;
    }

    public void setSelectedOrdering(Ordering selectedOrdering) {
        this.selectedOrdering = selectedOrdering;
    }

    public void refreshOrderingList(){
        Benutzer currentUser = userStore.getById(1L);
        orderingList = currentUser.getOrderingList().stream().filter(e-> e.getOrderState().equals(OrderState.BESTELLT)).collect(Collectors.toList());
    }
}

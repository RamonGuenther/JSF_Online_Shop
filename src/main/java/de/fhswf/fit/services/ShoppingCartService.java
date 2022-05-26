package de.fhswf.fit.services;

import de.fhswf.fit.entities.*;
import de.fhswf.fit.entities.enums.CategoryType;
import de.fhswf.fit.entities.enums.ImageType;
import de.fhswf.fit.stores.OrderStore;
import de.fhswf.fit.stores.ProductStore;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("shoppingCartService")
public class ShoppingCartService implements Serializable {

    private Ordering order;

    private List<OrderedProduct> orderedProductList;

    private transient OrderStore orderStore;

    private transient UserStore userStore;

    private transient ProductStore productStore;

    private User currentUser;

    private double totalPrice;

    private int totalAmount;


    public ShoppingCartService() {
        orderedProductList = new ArrayList<>();
        order = new Ordering();
    }

    private void init() {
        currentUser = userStore.getAll().get(0);
        order = currentUser.getOrderingList().get(0);
        orderedProductList.addAll(order.getOrderedProductList());
    }

    @Inject
    public void setOrderStore(OrderStore orderStore, UserStore userStore, ProductStore productStore) {
        System.out.println("init");
        this.orderStore = orderStore;
        this.userStore = userStore;
        this.productStore = productStore;
        init();
    }

    public Ordering getOrder() {
        return order;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrder(Ordering order) {
        this.order = order;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public void addToShoppingCart(Product product) {

        System.out.println("Aufruf");

        product.setInStock(product.getInStock() - 1);
        productStore.update(product);

        currentUser.getOrderingList().get(0).addOrderedProduct(new OrderedProduct(order, product, 1));

        orderStore.update(order);

        orderedProductList = currentUser.getOrderingList().get(0).getOrderedProductList();

//        orderStore.delete(order);

    }

    public double getTotalPrice(){
        refreshTotalPrice();
        return totalPrice;
    }

    public void refreshTotalPrice(){
        totalPrice = 0.0;
        for(OrderedProduct op : order.getOrderedProductList()){
            totalPrice+=op.getProduct().getPrice()*op.getAmount();
        }
        PrimeFaces.current().ajax().update("test");
    }

    public void onRowEdit(RowEditEvent<OrderedProduct> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        OrderedProduct editOP = event.getObject();

//        editOP.getProduct().setInStock(editOP.getProduct().getInStock()-editOP.getAmount());
//        productStore.update(editOP.getProduct()); TODO

        order.editOrderedProduct(editOP);
        orderStore.update(order);
    }

    public void onRowCancel(RowEditEvent<OrderedProduct> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int[] generateNumbers(OrderedProduct op){
        int[] result = new int[op.getProduct().getInStock()];
        int i = 0;
        while(i<op.getProduct().getInStock()) {
            result[i]=i+1;
            i++;
        }
        return result;
    }

}

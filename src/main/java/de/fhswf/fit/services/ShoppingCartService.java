package de.fhswf.fit.services;

import de.fhswf.fit.entities.*;
import de.fhswf.fit.stores.OrderStore;
import de.fhswf.fit.stores.OrderedProductStore;
import de.fhswf.fit.stores.ProductStore;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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

    private transient OrderedProductStore orderedProductStore;

    private Benutzer currentBenutzer;

    private double totalPrice;

    private int tmp;

    private String selectedAddress;


    public ShoppingCartService() {
        orderedProductList = new ArrayList<>();
        order = new Ordering();
    }

    private void init() {
        currentBenutzer = userStore.getAll().get(0);
        order = currentBenutzer.getOrderingList().get(0);
        orderedProductList.addAll(order.getOrderedProductList());
    }

    @Inject
    public void setOrderStore(OrderStore orderStore, UserStore userStore, ProductStore productStore
            , OrderedProductStore orderedProductStore) {
        System.out.println("init");
        this.orderStore = orderStore;
        this.userStore = userStore;
        this.productStore = productStore;
        this.orderedProductStore = orderedProductStore;
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

        currentBenutzer.getOrderingList().get(0).addOrderedProduct(new OrderedProduct(order, product, 1));

        orderStore.update(order);

        orderedProductList = currentBenutzer.getOrderingList().get(0).getOrderedProductList();

    }

    public double getTotalPrice() {
        refreshTotalPrice();
        return totalPrice;
    }

    public void refreshTotalPrice() {
        System.out.println("refreshTotal");
        totalPrice = 0.0;
        for (OrderedProduct op : order.getOrderedProductList()) {
            totalPrice += op.getProduct().getPrice() * op.getAmount();
        }
    }

    public void onRowEdit(RowEditEvent<OrderedProduct> event) {
        System.out.println("ONROWEDIT");
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        OrderedProduct editOP = event.getObject();

        editOP.getProduct().setInStock(editOP.getProduct().getInStock() + tmp - editOP.getAmount());
        productStore.update(editOP.getProduct());

        order.editOrderedProduct(editOP);
        orderStore.update(order);

    }

    public void onRowCancel(RowEditEvent<OrderedProduct> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int[] generateNumbers(OrderedProduct op) {
        tmp = op.getAmount(); //Für Später in onRowEdit
        int[] result = new int[op.getProduct().getInStock() + op.getAmount()];
        int i = 0;
        while (i < op.getProduct().getInStock() + op.getAmount()) {
            result[i] = i + 1;
            i++;
        }
        return result;
    }


    public void deleteProduct(OrderedProduct orderedProduct) {
        System.out.println("DELETE");


        orderedProduct.getProduct().setInStock(orderedProduct.getProduct().getInStock() + orderedProduct.getAmount());
        productStore.update(orderedProduct.getProduct());


        order.removeOrderedProduct(orderedProduct);
        orderStore.update(order);

        orderedProductStore.delete(orderedProduct);


        orderedProductList = order.getOrderedProductList();

    }

    public Benutzer getCurrentUser() {
        return currentBenutzer;
    }

    public void setCurrentUser(Benutzer currentBenutzer) {
        this.currentBenutzer = currentBenutzer;
    }

    public List<String> userAddresses() {
        List<String> result = new ArrayList<>();
        for (Address address : currentBenutzer.getAddressList()) {
            result.add(address.getFullAddress());
        }
        return result;
    }

    public void saveBillingAddress(AjaxBehaviorEvent event) {
        System.out.println(selectedAddress);
        for (Address address : currentBenutzer.getAddressList()) {
            if (address.getFullAddress().equals(this.selectedAddress)) {
                order.setBillingAddress(address);
                orderStore.update(order);
            }
        }

    }

    public void saveDeliveryAddress(AjaxBehaviorEvent event) {
        System.out.println(selectedAddress);
        for (Address address : currentBenutzer.getAddressList()) {
            if (address.getFullAddress().equals(this.selectedAddress)) {
                order.setDeliveryAddress(address);
                orderStore.update(order);
            }
        }

    }


    public String getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(String selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public void completeOrderingProcess() {
        System.out.println("complete Order");
        order.setOrderStateToOrder();
        orderStore.update(order);

        Ordering ordering = new Ordering();
        currentBenutzer.addOrder(ordering);
        userStore.update(currentBenutzer);

        order = ordering;
        orderedProductList = order.getOrderedProductList();

    }
}

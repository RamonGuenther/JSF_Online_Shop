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

    private transient OrderStore orderStore;
    private transient UserStore userStore;
    private transient ProductStore productStore;
    private transient OrderedProductStore orderedProductStore;
    private Ordering order;
    private List<OrderedProduct> orderedProductList;
    private int productAmountBefore;
    private String selectedAddress;
    private List<String> userAddresses;

    public ShoppingCartService() {
        orderedProductList = new ArrayList<>();
        order = new Ordering();
        userAddresses = new ArrayList<>();
    }

    private void init() {
        Benutzer currentUser = userStore.getById(1L);
        order = currentUser.getOrderingList().get(currentUser.getOrderingList().size() - 1);
        orderedProductList = order.getOrderedProductList();
        createUserAddresses();
    }

    @Inject
    public void setOrderStore(OrderStore orderStore, UserStore userStore, ProductStore productStore, OrderedProductStore orderedProductStore) {
        System.out.println("init");
        this.orderStore = orderStore;
        this.userStore = userStore;
        this.productStore = productStore;
        this.orderedProductStore = orderedProductStore;
        init();
    }


    public void addToShoppingCart(Product product, int amount) {
        if(amount == 0){
            amount = 1;
        }

        System.out.println("Aufruf");

        product.setInStock(product.getInStock() - amount);
        productStore.update(product);

        order.addOrderedProduct(new OrderedProduct(order, product, amount), amount);

        orderStore.update(order);

        orderedProductList = order.getOrderedProductList();

    }

    public void onRowEdit(RowEditEvent<OrderedProduct> event) {
        System.out.println("ONROWEDIT");
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        OrderedProduct editOP = event.getObject();

        Product product = productStore.getById(editOP.getProduct().getId());

        product.setInStock(product.getInStock() + productAmountBefore - editOP.getAmount());

        productStore.update(product);

        order.editOrderedProduct(editOP);
        orderStore.update(order);

    }

    public void onRowCancel(RowEditEvent<OrderedProduct> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getProduct().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Methode um die verfügbare Produktanzahl zu ermitteln, damit der Nutzer im Warenkorb die Menge anpassen kann
     */
    public List<Integer> generateAmountNumbers(OrderedProduct op) {
        productAmountBefore = op.getAmount(); //Für Später in onRowEdit
        Product product = productStore.getById(op.getProduct().getId());

        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < product.getInStock() + op.getAmount()) {
            result.add(i + 1);
            i++;
        }
        return result;
    }


    public void createUserAddresses(){
        Benutzer currentUser = userStore.getById(1L);
        userAddresses = new ArrayList<>();
        for (Address address : currentUser.getAddressList()) {
            userAddresses.add(address.getFullAddress());
        }
        System.out.println(userAddresses.size());
    }

    public void deleteProduct(OrderedProduct orderedProduct) {
        System.out.println("DELETE");

        Product product = productStore.getById(orderedProduct.getProduct().getId());
        product.setInStock(product.getInStock() + orderedProduct.getAmount());

        productStore.update(product);

        order.removeOrderedProduct(orderedProduct);
        orderStore.update(order);

        orderedProductStore.delete(orderedProduct);

        orderedProductList = order.getOrderedProductList();
    }


    public void saveBillingAddress(AjaxBehaviorEvent event) {
        Benutzer currentUser = userStore.getById(1L);
        for (Address address : currentUser.getAddressList()) {
            if (address.getFullAddress().equals(this.selectedAddress)) {
                order.setBillingAddress(address);
                orderStore.update(order);
            }
        }

    }

    public void saveDeliveryAddress(AjaxBehaviorEvent event) {
        Benutzer currentUser = userStore.getById(1L);

        System.out.println(selectedAddress);
        for (Address address : currentUser.getAddressList()) {
            if (address.getFullAddress().equals(this.selectedAddress)) {
                order.setDeliveryAddress(address);
                orderStore.update(order);
            }
        }

    }

    /**
     * Methode um den Bestellvorgang abzuschließen
     */
    public void completeOrderingProcess() {
        Benutzer currentUser = userStore.getById(1L);

        System.out.println("complete Order");
        order.setOrderStateToOrder();
        orderStore.update(order);

        Ordering ordering = new Ordering();
        orderStore.save(ordering);
        currentUser.addOrder(ordering);
        userStore.update(currentUser);

        order = currentUser.getOrderingList().get(currentUser.getOrderingList().size() - 1);

        orderedProductList = order.getOrderedProductList();
        selectedAddress = "";
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
    public String getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(String selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public List<String> getUserAddresses() {
        return userAddresses;
    }

}

package de.fhswf.fit.services;

import de.fhswf.fit.entities.*;
import de.fhswf.fit.entities.enums.CategoryType;
import de.fhswf.fit.entities.enums.ImageType;
import de.fhswf.fit.stores.OrderStore;
import de.fhswf.fit.stores.ProductStore;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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
    }
}

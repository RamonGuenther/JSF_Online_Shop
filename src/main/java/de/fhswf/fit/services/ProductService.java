package de.fhswf.fit.services;

import de.fhswf.fit.entities.OrderedProduct;
import de.fhswf.fit.entities.Ordering;
import de.fhswf.fit.entities.Product;
import de.fhswf.fit.entities.User;
import de.fhswf.fit.stores.OrderStore;
import de.fhswf.fit.stores.ProductStore;
import de.fhswf.fit.stores.UserStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("productService")
@SessionScoped
public class ProductService implements Serializable {

    private Product product;

    private Product selectedProduct;

    private List<Product> productList;
    private transient ProductStore productStore;

    public ProductService() {
        product = new Product();
        productList = new ArrayList<>();
    }

    private void init(){
        productList.addAll(productStore.getAll().stream().filter(filter -> filter.getInStock() >= 1).collect(Collectors.toList()));
    }


    @Inject
    public void setProductStore(ProductStore productStore){
        this.productStore = productStore;
        init();
    }

    public Product getProduct() {
        return product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void refreshProducts(){
        System.out.println("Refresh");
        productList = productStore.getAll().stream().filter(filter -> filter.getInStock() >= 1).collect(Collectors.toList());
    }
}

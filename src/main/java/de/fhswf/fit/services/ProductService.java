package de.fhswf.fit.services;

import de.fhswf.fit.entities.Product;
import de.fhswf.fit.stores.ProductStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("productService")
public class ProductService implements Serializable {

    private Product product;

    private Product selectedProduct;

    private List<Product> productList;
    private transient ProductStore productStore;

    public ProductService() {
        product = new Product();
        productList = new ArrayList<>();
    }


    @Inject
    public void setProductStore(ProductStore productStore){
        this.productStore = productStore;
    }

    public Product getProduct() {
        return product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public List<Product> getProductList() {
        productList = productStore.getAll();
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



}

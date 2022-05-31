package de.fhswf.fit.services;

import de.fhswf.fit.entities.Product;
import de.fhswf.fit.stores.ProductStore;
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

    private String amount;
    private List<Integer> inStockAmountList;

    public ProductService() {
        product = new Product();
        productList = new ArrayList<>();
        inStockAmountList = new ArrayList<>();
    }

    private void init(){
        productList.addAll(productStore.getAll().stream().filter(filter -> filter.getInStock() >= 1).collect(Collectors.toList()));
    }


    @Inject
    public void setProductStore(ProductStore productStore){
        this.productStore = productStore;
        init();
    }

    public void refreshProducts(){
        System.out.println("Refresh Products");
        productList = productStore.getAll().stream().filter(filter -> filter.getInStock() >= 1).collect(Collectors.toList());
    }

    private void generateAmountNumbers() {
        inStockAmountList = new ArrayList<>();
        int i = 0;
        while (i < selectedProduct.getInStock()) {
            inStockAmountList.add(i + 1);
            i++;
        }
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<Integer> getInStockAmountList() {
        generateAmountNumbers();
        return inStockAmountList;
    }

    public void setInStockAmountList(List<Integer> inStockAmountList) {
        this.inStockAmountList = inStockAmountList;
    }
}

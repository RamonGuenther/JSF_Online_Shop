package de.fhswf.fit.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;



    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Image> imageList;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Category> categoryList;

    private int inStock;

    private String description;


    public Product(String name, double price, int inStock, String description) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
        categoryList = new HashSet<>();
        imageList = new HashSet<>();
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Set<Category> getCategoryList() {
        return categoryList;
    }

    public int getInStock() {
        return inStock;
    }

    public String getDescription() {
        return description;
    }

    public Set<Image> getImageList() {
        return imageList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageList(Set<Image> imageList) {
        this.imageList = imageList;
    }

    public void addImage(Image image){
        imageList.add(image);
    }

    public void addCategory(Category category){
        categoryList.add(category);
    }
}

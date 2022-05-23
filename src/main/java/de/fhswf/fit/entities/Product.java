package de.fhswf.fit.entities;

import de.fhswf.fit.entities.enums.CategoryType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Serializable {

    @Id
    private String id;

    private String name;

    private double price;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Image> imageList;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Category> categoryList;

    private CategoryType mainCategory;

    private int inStock;

    private String description;


    public Product(String name, double price, int inStock, String description, CategoryType mainCategory) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
        this.mainCategory = mainCategory;
        categoryList = new HashSet<>();
        imageList = new ArrayList<>();
        id = UUID.randomUUID().toString();
    }

    public Product() {

    }

    public String getId() {
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

    public List<Image> getImageList() {
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

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public void addImage(Image image) {
        imageList.add(image);
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public String getFirstImage() {
        return imageList.get(0).getName();
    }

    public CategoryType getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(CategoryType mainCategory) {
        this.mainCategory = mainCategory;
    }
}



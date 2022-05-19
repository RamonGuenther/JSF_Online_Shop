package de.fhswf.fit.entities;

import de.fhswf.fit.entities.enums.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CategoryType categoryType;

    private String description;

    public Category(CategoryType categoryType, String description) {
        this.categoryType = categoryType;
        this.description = description;
    }

    public Category() {

    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public String getDescription() {
        return description;
    }
}

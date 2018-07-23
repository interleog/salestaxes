package com.salestaxes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @NotNull
    @Column(name = "CATEGORY")
    private String category;

    @NotNull
    @Column(name = "IMPORTED")
    private boolean isImported;

    @NotNull
    @Column(name = "PRICE")
    private Double price;

    public Product() {
    }

    public Product(Long id, String cod, String des, String category, boolean isImported, Double price) {
        super(id, cod, des);
        this.category = category;
        this.isImported = isImported;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getDes() + ": " + price + "\n";
    }
}

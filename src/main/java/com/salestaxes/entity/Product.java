package com.salestaxes.entity;

import javax.validation.constraints.NotNull;

public class Product extends BaseEntity {

    @NotNull
    private String category;

    @NotNull
    private boolean isImported;

    @NotNull
    private Double price;

    @NotNull
    private Long quantity;

    public Product() {
    }

    public Product(Long id, String cod, String des, String category, boolean isImported, Double price, Long quantity) {
        super(id, cod, des);
        this.category = category;
        this.isImported = isImported;
        this.price = price;
        this.quantity = quantity;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity + " " + this.getDes() + ": " + price + "\n";
    }
}

package com.salestaxes.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public class Product extends BaseEntity {

    @NotNull
    private String category;

    @NotNull
    private boolean isImported;

    @NotNull
    private Float price;

    @NotNull
    private Long quantity;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

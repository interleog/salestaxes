package com.salestaxes.utils;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public enum ProductCategoryEnum {

    BOOKS("BOOKS"),
    FOOD("FOOD"),
    MEDICINES("MEDICINES");

    private final String category;

    private ProductCategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

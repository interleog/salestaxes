package com.salestaxes.entity;

import java.util.List;

/**
 * Created by Leonardo Galati on 06/09/2017.
 */
public class ShoppingCart extends BaseEntity {

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

package com.salestaxes.entity;

import javax.validation.constraints.NotNull;
import java.util.List;


public class ShoppingCart extends BaseEntity {

    @NotNull
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder("Input " + this.getId() + ": \n");

        productList.forEach(p -> {
            output.append(p.toString());
        });

        return output.toString();
    }

}

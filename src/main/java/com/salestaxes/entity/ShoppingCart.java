package com.salestaxes.entity;

import java.util.List;

/**
 * Created by Leonardo Galati on 06/09/2017.
 */
public class ShoppingCart extends BaseEntity {

    private List<Product> productList;

    private Float totalTaxes;

    private Float grandTotal;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Float getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(Float totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }
}

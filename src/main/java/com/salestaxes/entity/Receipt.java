package com.salestaxes.entity;

import java.util.List;

public class Receipt extends BaseEntity {
    private List<Product> productList;

    private Double totalTaxes;

    private Double grandTotal;

    public Receipt(List<Product> productList, Double totalTaxes, Double grandTotal) {
        this.productList = productList;
        this.totalTaxes = totalTaxes;
        this.grandTotal = grandTotal;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Double getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(Double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder("Output " + this.getId() + ": \n");

        productList.forEach(p -> {
            output.append(p.toString());
        });

        output.append("Sales taxes: " + totalTaxes + "\n").append("Total: " + grandTotal + "\n");

        return output.toString();
    }
}

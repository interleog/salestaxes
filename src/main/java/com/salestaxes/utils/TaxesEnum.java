package com.salestaxes.utils;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public enum TaxesEnum {

    GENERAL_TAX(10),
    IMPORTED_TAX(5);

    private final int tax;

    private TaxesEnum(int tax) {
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
}

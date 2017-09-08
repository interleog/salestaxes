package com.salestaxes.utils;

public enum TaxesEnum {

    GENERAL_TAX(10.00D),
    IMPORTED_TAX(5.00D),
    HUNDRED(100.00D);

    private final Double tax;

    TaxesEnum(Double tax) {
        this.tax = tax;
    }

    public Double getValue() {
        return tax;
    }
}

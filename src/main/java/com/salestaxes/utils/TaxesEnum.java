package com.salestaxes.utils;

import java.math.BigDecimal;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public enum TaxesEnum {

    GENERAL_TAX(10.00D),
    IMPORTED_TAX(5.00D),
    HUNDRED(100.00D);

    private final Double tax;

    private TaxesEnum(Double tax) {
        this.tax = tax;
    }

    public Double getValue() {
        return tax;
    }
}

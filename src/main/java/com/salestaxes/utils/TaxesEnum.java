package com.salestaxes.utils;

import java.math.BigDecimal;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public enum TaxesEnum {

    GENERAL_TAX(BigDecimal.valueOf(10)),
    IMPORTED_TAX(BigDecimal.valueOf(5)),
    HUNDRED(BigDecimal.valueOf(100));

    private final BigDecimal tax;

    private TaxesEnum(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getValue() {
        return tax;
    }
}

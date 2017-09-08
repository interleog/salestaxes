package com.salestaxes.serviceImpl;

import com.salestaxes.entity.Product;
import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;
import com.salestaxes.service.OrdersService;
import com.salestaxes.utils.ProductCategoryEnum;
import com.salestaxes.utils.TaxesEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


@Service
public class OrdersServiceImpl implements OrdersService {

    /**
     * Core method of the application. It takes as an input a ShoppingCart object, which contains the list of
     * products, then calculate taxes and price for every product, and return the Receipt object.
     *
     * @param shoppingCart ShoppingCart object
     * @return Receipt object
     */
    @Override
    public Receipt processOrder(final ShoppingCart shoppingCart) {

        Receipt rcpt = new Receipt(new ArrayList<>(), 0.00D, 0.00D);

        if (!shoppingCart.getProductList().isEmpty()) {

            rcpt.setId(shoppingCart.getId());
            rcpt.setCod(shoppingCart.getCod());
            rcpt.setDes("Receipt for shopping cart n. " + shoppingCart.getId());

            shoppingCart.getProductList()
                    .forEach(item -> {
                        Product p = new Product();
                        p.setId(item.getId());
                        p.setCod(item.getCod());
                        p.setDes(item.getDes());
                        p.setCategory(item.getCategory());
                        p.setImported(item.isImported());
                        p.setQuantity(item.getQuantity());
                        Double productTaxes = calculateTaxes(item);
                        p.setPrice(p.getQuantity() * roundDecimals(Double.sum(item.getPrice(), productTaxes), 2));
                        rcpt.setTotalTaxes(roundDecimals(Double.sum(rcpt.getTotalTaxes(), productTaxes), 2));
                        rcpt.setGrandTotal(roundDecimals(Double.sum(rcpt.getGrandTotal(), p.getPrice()), 2));

                        rcpt.getProductList().add(p);
                    });

        }

        return rcpt;
    }

    /**
     * This method is used to calculate the taxes of a product.
     *
     * @param product Product object
     * @return Double number
     */
    private Double calculateTaxes(final Product product) {
        Double taxes = 0.00D;
        Double importTaxes = 0.00D;

        if (!isTaxFree(product)) {
            taxes = (product.getPrice() * TaxesEnum.GENERAL_TAX.getValue()) / TaxesEnum.HUNDRED.getValue();
            taxes = roundTax(taxes);
        }

        if (product.isImported()) {
            importTaxes = product.getPrice() * TaxesEnum.IMPORTED_TAX.getValue() / TaxesEnum.HUNDRED.getValue();
            importTaxes = roundTax(importTaxes);
        }

        return product.getQuantity() * roundDecimals(Double.sum(taxes, importTaxes), 2);
    }

    /**
     * This method round the tax value to the nearest 0.05.
     *
     * @param tax Product tax value
     * @return Rounded tax to the nearest 0.05
     */
    private Double roundTax(final Double tax) {
        Double roundedTax = roundDecimals(tax, 2);
        return Math.ceil(roundedTax / 0.05) * 0.05;
    }

    /**
     * Method used to round a number to n decimal places.
     *
     * @param value The number that has to be rounded
     * @param places Number of decimal places
     * @return Double rounded number
     */
    private Double roundDecimals(final Double value, final int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Method used to check if a product is tax exempt.
     *
     * @param p Product object
     * @return Boolean value
     */
    private boolean isTaxFree(final Product p) {
        return p.getCategory().equalsIgnoreCase(ProductCategoryEnum.BOOKS.getCategory()) ||
                p.getCategory().equalsIgnoreCase(ProductCategoryEnum.FOOD.getCategory()) ||
                p.getCategory().equalsIgnoreCase(ProductCategoryEnum.MEDICINES.getCategory());
    }
}

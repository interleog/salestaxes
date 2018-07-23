package com.salestaxes.serviceImpl;

import com.salestaxes.entity.Product;
import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;
import com.salestaxes.entity.ShoppingCartItem;
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
     * products, then calculates taxes and price for every product, and returns a Receipt object.
     *
     * @param shoppingCart ShoppingCart object
     * @return Receipt object
     */
    @Override
    public Receipt processOrder(final ShoppingCart shoppingCart) {

        Receipt rcpt = new Receipt(new ArrayList<>(), 0.00D, 0.00D);

        if (shoppingCart != null && !shoppingCart.getItemList().isEmpty()) {

            rcpt.setId(shoppingCart.getId());
            rcpt.setCod(shoppingCart.getCod());
            rcpt.setDes("Receipt for shopping cart n. " + shoppingCart.getId());

            shoppingCart.getItemList()
                    .forEach(item -> {
                        Product p = new Product();
                        p.setId(item.getProduct().getId());
                        p.setCod(item.getProduct().getCod());
                        p.setDes(item.getProduct().getDes());
                        p.setCategory(item.getProduct().getCategory());
                        p.setImported(item.getProduct().isImported());
                        //p.setQuantity(item.getQuantity());
                        Double productTaxes = calculateTaxes(item.getProduct(), item.getQuantity());
                        p.setPrice(item.getQuantity() * roundDecimals(Double.sum(item.getProduct().getPrice(), productTaxes), 2));
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
    private Double calculateTaxes(final Product product, final Long quantity) {
        Double taxes = 0.00D;
        Double importTaxes = 0.00D;

        if (!isTaxFree(product)) {
            taxes = calculatePercentage(product.getPrice(), TaxesEnum.GENERAL_TAX.getValue());
            taxes = roundTax(taxes);
        }

        if (product.isImported()) {
            importTaxes = calculatePercentage(product.getPrice(), TaxesEnum.IMPORTED_TAX.getValue());
            importTaxes = roundTax(importTaxes);
        }

        return quantity * roundDecimals(Double.sum(taxes, importTaxes), 2);
    }

    /**
     * Method used to calculate a given percentage of a given value.
     *
     * @param value The value on which calculate percentage
     * @param percentage Percentage to be calculated
     * @return The percentage value
     */

    private Double calculatePercentage(final Double value, final Double percentage) {
        return (value * percentage) / TaxesEnum.HUNDRED.getValue();
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

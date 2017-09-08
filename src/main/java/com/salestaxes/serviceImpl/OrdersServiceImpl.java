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

    private Double roundTax(final Double tax) {
        Double roundedTax = roundDecimals(tax, 2);
        return Math.ceil(roundedTax / 0.05) * 0.05;
    }

    private Double roundDecimals(final Double value, final int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private boolean isTaxFree(final Product p) {
        return p.getCategory().equalsIgnoreCase(ProductCategoryEnum.BOOKS.getCategory()) ||
                p.getCategory().equalsIgnoreCase(ProductCategoryEnum.FOOD.getCategory()) ||
                p.getCategory().equalsIgnoreCase(ProductCategoryEnum.MEDICINES.getCategory());
    }
}

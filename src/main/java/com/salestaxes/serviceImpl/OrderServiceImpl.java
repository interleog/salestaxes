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
import java.util.List;


@Service
public class OrderServiceImpl implements OrdersService {

    @Override
    public Receipt processOrder(ShoppingCart shoppingCart) {

        Receipt rcpt = new Receipt();
        List<Product> rcptProducts = new ArrayList<>();

        rcpt.setId(shoppingCart.getId());
        rcpt.setCod(shoppingCart.getCod());
        rcpt.setDes("Recepit for shopping cart n. "+shoppingCart.getId());
        rcpt.setTotalTaxes(0D);
        rcpt.setGrandTotal(0D);

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
                    p.setPrice(roundDecimals(Double.sum(item.getPrice(), productTaxes), 2));
                    rcpt.setTotalTaxes(roundDecimals(Double.sum(rcpt.getTotalTaxes(), productTaxes), 2));
                    rcpt.setGrandTotal(roundDecimals(Double.sum(rcpt.getGrandTotal(), p.getPrice()), 2));

                    rcptProducts.add(p);
                });

        rcpt.setProductList(rcptProducts);

        return rcpt;
    }

    private Double calculateTaxes(Product product) {
        Double taxes = 0.00;
        Double importTaxes = 0.00;

        if (!product.getCategory().equalsIgnoreCase(ProductCategoryEnum.BOOKS.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.FOOD.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.MEDICINES.getCategory())) {

            taxes = (product.getPrice() * TaxesEnum.GENERAL_TAX.getValue()) / TaxesEnum.HUNDRED.getValue();
            taxes = roundDecimals(taxes, 2);
            taxes = roundTax(taxes);

        }

        if (product.isImported()) {
            importTaxes = product.getPrice() * TaxesEnum.IMPORTED_TAX.getValue() / TaxesEnum.HUNDRED.getValue();
            importTaxes = roundDecimals(importTaxes, 2);
            importTaxes = roundTax(importTaxes);

        }

        return Double.sum(taxes, importTaxes);
    }

    private Double roundTax(Double tax) {
        return Math.ceil(tax / 0.05) * 0.05;
    }

    private double roundDecimals(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /*private Float calculateTaxes(Product product) {
        BigDecimal taxes = BigDecimal.ZERO;
        BigDecimal importTaxes = BigDecimal.ZERO;

        Double totalTaxes = 0D;

        if (!product.getCategory().equalsIgnoreCase(ProductCategoryEnum.BOOKS.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.FOOD.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.MEDICINES.getCategory())) {

            taxes = BigDecimal.valueOf(product.getPrice()).multiply(TaxesEnum.GENERAL_TAX.getValue()).divide(TaxesEnum.HUNDRED.getValue(), 2, RoundingMode.HALF_UP);
        }

        if (product.isImported()) {
            importTaxes = BigDecimal.valueOf(product.getPrice()).multiply(TaxesEnum.IMPORTED_TAX.getValue()).divide(TaxesEnum.HUNDRED.getValue(), 2, RoundingMode.HALF_UP);
        }

        totalTaxes = (Math.ceil(taxes.floatValue() * 2) / 2) + (Math.ceil(importTaxes.floatValue() * 2) / 2);

        return totalTaxes.floatValue();

    }*/
}

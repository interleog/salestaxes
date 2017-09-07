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
        rcpt.setTotalTaxes(0F);
        rcpt.setGrandTotal(0F);

        shoppingCart.getProductList()
                .forEach(item -> {
                    Product p = new Product();
                    p.setId(item.getId());
                    p.setCod(item.getCod());
                    p.setDes(item.getDes());
                    p.setCategory(item.getCategory());
                    p.setImported(item.isImported());
                    p.setQuantity(item.getQuantity());
                    Float productTaxes = calculateTaxes(item);
                    p.setPrice(Float.sum(item.getPrice(), productTaxes));
                    rcpt.setTotalTaxes(Float.sum(rcpt.getTotalTaxes(), productTaxes));
                    rcpt.setGrandTotal(Float.sum(rcpt.getGrandTotal(), p.getPrice()));

                    rcptProducts.add(p);
                });

        rcpt.setProductList(rcptProducts);

        return rcpt;
    }

    private Float calculateTaxes(Product product) {
        BigDecimal taxes = BigDecimal.ZERO;
        BigDecimal importTaxes = BigDecimal.ZERO;

        taxes = taxes.setScale(2, 5);

        if (!product.getCategory().equalsIgnoreCase(ProductCategoryEnum.BOOKS.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.FOOD.getCategory()) &&
                !product.getCategory().equalsIgnoreCase(ProductCategoryEnum.MEDICINES.getCategory())) {

            taxes = BigDecimal.valueOf(product.getPrice()).multiply(TaxesEnum.GENERAL_TAX.getValue()).divide(TaxesEnum.HUNDRED.getValue(), 2, RoundingMode.HALF_UP);
        }

        if (product.isImported()) {
            importTaxes = BigDecimal.valueOf(product.getPrice()).multiply(TaxesEnum.IMPORTED_TAX.getValue()).divide(TaxesEnum.HUNDRED.getValue(), 2, RoundingMode.HALF_UP);
        }

        return taxes.add(importTaxes).floatValue();

    }
}

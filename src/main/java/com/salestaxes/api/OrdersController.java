package com.salestaxes.api;

import com.salestaxes.entity.Product;
import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;
import com.salestaxes.service.OrdersService;
import com.salestaxes.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * Method used to insert a new order and generate a receipt.
     *
     * @param shoppingCart ShoppingCart object
     * @return Receipt object
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = "application/json")
    public GenericResponse<Receipt> insert(@RequestBody @Valid ShoppingCart shoppingCart) {

        Receipt rcpt = ordersService.processOrder(shoppingCart);

        if (!rcpt.getProductList().isEmpty()) {
            return new GenericResponse<>(rcpt, 0);
        } else {
            return new GenericResponse<>(null, 1);
        }

    }

    @RequestMapping(value = "/process", method = RequestMethod.GET, produces = "application/json")
    public GenericResponse<Receipt> process() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Product> productList = new ArrayList<>();
        Product p = new Product(1L, "1", "book", "BOOKS", false, 12.49D, 1L);
        productList.add(p);
        p = new Product(2L, "2", "music CD", "OTHER", false, 14.99D, 1L);
        productList.add(p);
        p = new Product(3L, "3", "chocolate bar", "FOOD", false, 0.85D, 1L);
        productList.add(p);

        shoppingCart.setId(1L);
        shoppingCart.setCod("CART1");
        shoppingCart.setDes("Input 1");
        shoppingCart.setProductList(productList);
        Receipt rcpt = ordersService.processOrder(shoppingCart);

        if (!rcpt.getProductList().isEmpty()) {
            return new GenericResponse<>(rcpt, 0);
        } else {
            return new GenericResponse<>(null, 1);
        }
    }

}

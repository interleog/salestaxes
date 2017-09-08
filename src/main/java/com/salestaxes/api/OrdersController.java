package com.salestaxes.api;

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


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrdersController {

    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

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

}

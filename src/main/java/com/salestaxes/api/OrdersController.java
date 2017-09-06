package com.salestaxes.api;

import com.salestaxes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrdersController {

    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


}

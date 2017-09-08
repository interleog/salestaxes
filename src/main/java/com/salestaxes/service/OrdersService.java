package com.salestaxes.service;

import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;


public interface OrdersService {

    Receipt processOrder (final ShoppingCart shoppingCart);



}

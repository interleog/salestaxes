package com.salestaxes.service;

import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
public interface OrdersService {

    Receipt processOrder (final ShoppingCart shoppingCart);





}

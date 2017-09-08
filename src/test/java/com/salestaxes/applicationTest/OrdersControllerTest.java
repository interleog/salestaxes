package com.salestaxes.applicationTest;

import com.salestaxes.SalesTaxesApplication;
import com.salestaxes.api.OrdersController;
import com.salestaxes.entity.Product;
import com.salestaxes.entity.Receipt;
import com.salestaxes.entity.ShoppingCart;
import com.salestaxes.utils.GenericResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalesTaxesApplication.class)
public class OrdersControllerTest {

    @Autowired
    private OrdersController ordersController;

    @Test
    public void input1() {
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

        GenericResponse<Receipt> output = ordersController.insert(shoppingCart);

        System.out.println();
        System.out.println(output.getData().toString());
        System.out.println();
    }

    @Test
    public void input2() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Product> productList = new ArrayList<>();
        Product p = new Product(1L, "1", "imported box of chocolates", "FOOD", true, 10.00D, 1L);
        productList.add(p);
        p = new Product(2L, "2", "imported bottle of perfume", "OTHER", true, 47.50D, 1L);
        productList.add(p);

        shoppingCart.setId(2L);
        shoppingCart.setCod("CART2");
        shoppingCart.setDes("Input 2");
        shoppingCart.setProductList(productList);

        GenericResponse<Receipt> output = ordersController.insert(shoppingCart);

        System.out.println();
        System.out.println(output.getData().toString());
        System.out.println();
    }

    @Test
    public void input3() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Product> productList = new ArrayList<>();
        Product p = new Product(1L, "1", "imported bottle of perfume", "OTHER", true, 27.99D, 1L);
        productList.add(p);
        p = new Product(2L, "2", "bottle of perfume", "OTHER", false, 18.99D, 1L);
        productList.add(p);
        p = new Product(3L, "3", "packet of headache pills", "MEDICINES", false, 9.75D, 1L);
        productList.add(p);
        p = new Product(4L, "4", "box of imported chocolate", "FOOD", true, 11.25D, 1L);
        productList.add(p);

        shoppingCart.setId(3L);
        shoppingCart.setCod("CART3");
        shoppingCart.setDes("Input 3");
        shoppingCart.setProductList(productList);

        GenericResponse<Receipt> output = ordersController.insert(shoppingCart);

        System.out.println();
        System.out.println(output.getData().toString());
        System.out.println();
    }

    @Test
    public void generateAllRecepits() {
        input1();
        input2();
        input3();
    }

}

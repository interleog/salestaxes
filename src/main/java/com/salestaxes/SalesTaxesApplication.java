package com.salestaxes;

import com.salestaxes.dao.ProductRepository;
import com.salestaxes.dao.ShoppingCartItemRepository;
import com.salestaxes.dao.ShoppingCartRepository;
import com.salestaxes.entity.Product;
import com.salestaxes.entity.ShoppingCart;
import com.salestaxes.entity.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@ComponentScan(basePackages = {"com.salestaxes"})
public class SalesTaxesApplication {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;


    public static void main(String[] args) {

        SpringApplication.run(SalesTaxesApplication.class, args);

    }

    @PostConstruct
    public void initDatabase() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setCod("CART1");
        shoppingCart.setDes("Input 1");
        shoppingCartRepository.save(shoppingCart);

        Product p = new Product(1L, "1", "book", "BOOKS", false, 12.49D);
        productRepository.save(p);
        Product p2 = new Product(2L, "2", "music CD", "OTHER", false, 14.99D);
        productRepository.save(p2);
        Product p3 = new Product(3L, "3", "chocolate bar", "FOOD", false, 0.85D);
        productRepository.save(p3);


        ShoppingCartItem item = new ShoppingCartItem(1L, "item1", "cart1 - item1", p, 1L, shoppingCart);
        shoppingCartItemRepository.save(item);
        ShoppingCartItem item2 = new ShoppingCartItem(2L, "item2", "cart1 - item2", p2, 1L, shoppingCart);
        shoppingCartItemRepository.save(item2);
        ShoppingCartItem item3  = new ShoppingCartItem(3L, "item3", "cart1 - item3", p3, 1L, shoppingCart);
        shoppingCartItemRepository.save(item3);
    }

    @PreDestroy
    public void destroyDatabase() {
        shoppingCartItemRepository.deleteAll();
        productRepository.deleteAll();
        shoppingCartRepository.deleteAll();
    }

}
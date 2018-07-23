package com.salestaxes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SHOPPING_CART_ITEM")
public class ShoppingCartItem extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PRODUCT_FK")
    private Product product;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "SHOPPING_CART_FK")
    private ShoppingCart shoppingCart;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Long id, String cod, String des, Product product, Long quantity, ShoppingCart shoppingCart) {
        super(id, cod, des);
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}

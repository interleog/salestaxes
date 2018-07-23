package com.salestaxes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart extends BaseEntity {

    //@NotNull
    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER)
    private List<ShoppingCartItem> itemList;

    public List<ShoppingCartItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ShoppingCartItem> itemList) {
        this.itemList = itemList;
    }
}

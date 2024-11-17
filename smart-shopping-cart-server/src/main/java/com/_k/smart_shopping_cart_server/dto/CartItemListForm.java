package com._k.smart_shopping_cart_server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemListForm {
    private String cartId;
    private List<CartItemForm> cartItemFormList;

    public CartItemListForm() {}
    public CartItemListForm(String cartId, List<CartItemForm> cartItemFormList) {
        this.cartId = cartId;
        this.cartItemFormList = cartItemFormList;
    }
}

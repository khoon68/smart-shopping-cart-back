package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cart {
    private String cartId;
    @Setter
    private int orderId;

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public Cart(String cartId, int orderId) {
        this.cartId = cartId;
        this.orderId = orderId;
    }
}

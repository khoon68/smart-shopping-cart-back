package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Carts {
    private String id;
    @Setter
    private int orderId;

    public Carts(String id) {
        this.id = id;
    }

    public Carts(String id, int orderId) {
        this.id = id;
        this.orderId = orderId;
    }
}

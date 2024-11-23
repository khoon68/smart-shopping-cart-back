package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderDetails {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;

    public OrderDetails(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetails(int id, int orderId, int productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}

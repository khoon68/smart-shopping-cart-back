package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderDetails {
    private int orderDetailId;
    private int orderId;
    private int productId;
    private int productQuantity;

    public OrderDetails(int orderId, int productId, int productQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public OrderDetails(int orderDetailId, int orderId, int productId, int productQuantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

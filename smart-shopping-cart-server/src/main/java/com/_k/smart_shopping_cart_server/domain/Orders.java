package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
public class Orders {
    private int orderId;
    @Setter
    private String orderDateTime;

    public Orders() {}
    public Orders(int orderId, String orderDateTime) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
    }
}

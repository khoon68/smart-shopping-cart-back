package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Orders;

public interface OrdersService {
    int generateOrder();
    Orders showOrderById(int id);
    String fillInOrder(int orderId);

}

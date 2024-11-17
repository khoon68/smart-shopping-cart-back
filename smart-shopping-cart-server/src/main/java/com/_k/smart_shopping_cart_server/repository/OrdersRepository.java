package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    int saveOrder();
    Optional<Orders> readOrderByOrderId(int orderId);
    List<Orders> readAllOrder();
    int updateOrder(Orders order);
    void deleteAllOrder();
}

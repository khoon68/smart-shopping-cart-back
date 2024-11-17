package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    int generateOrderDetail(OrderDetails orderDetails);
    OrderDetails showOrderDetailByOrderDetailId(int orderDetailId);
    List<OrderDetails> showOrderDetailListByDetailId(int orderId);
}

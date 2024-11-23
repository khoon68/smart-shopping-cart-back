package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository {
    int saveOrderDetail(OrderDetails orderDetails);
    Optional<OrderDetails> readOrderDetailByOrderDetailId(int id);
    List<OrderDetails> readOrderDetailListByOrderId(int orderId);
    List<OrderDetails> readAllOrderDetail();
    void deleteAllOrderDetail();
}

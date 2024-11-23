package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.OrderDetails;
import com._k.smart_shopping_cart_server.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public int generateOrderDetail(OrderDetails orderDetails) {
        return orderDetailsRepository.saveOrderDetail(orderDetails);
    }

    @Override
    public OrderDetails showOrderDetailByOrderDetailId(int orderDetailId) {
        Optional<OrderDetails> orderDetailsNullable = orderDetailsRepository.readOrderDetailByOrderDetailId(orderDetailId);
        if (orderDetailsNullable.isEmpty()) throw new IllegalStateException(orderDetailId + "에 해당하는 구매 항목은 없습니다.");
        return orderDetailsNullable.get();
    }

    @Override
    public List<OrderDetails> showOrderDetailListByOrderId(int orderId) {
        return orderDetailsRepository.readOrderDetailListByOrderId(orderId);
    }
}

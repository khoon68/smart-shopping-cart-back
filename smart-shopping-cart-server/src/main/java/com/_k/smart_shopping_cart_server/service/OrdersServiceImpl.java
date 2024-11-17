package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Orders;
import com._k.smart_shopping_cart_server.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public int generateOrder() {
        return ordersRepository.saveOrder();
    }

    @Override
    public Orders showOrderById(int id) {
        Optional<Orders> orderById = ordersRepository.readOrderByOrderId(id);
        if (orderById.isEmpty()) throw new IllegalStateException(id + "에 해당하는 구매목록은 없습니다.");

        return orderById.get();
    }

    @Override
    public String fillInOrder(int orderId) {
        Optional<Orders> orderNullable = ordersRepository.readOrderByOrderId(orderId);
        if (orderNullable.isEmpty()) throw new IllegalStateException(orderId + "에 해당하는 구매목록은 없습니다.");
        Orders order = orderNullable.get();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String orderTime = sdf.format(timestamp);
        order.setOrderDateTime(orderTime);
        return orderTime;
    }
}

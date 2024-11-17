package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Orders;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcOrdersRepositoryTest {
    @Autowired
    OrdersRepository ordersRepository;

    @Test
    void insertAndReadOrderTest() {
        int savedOrderId = ordersRepository.saveOrder();
        Orders readOrder = ordersRepository.readOrderByOrderId(savedOrderId).get();

        Assertions.assertThat(readOrder.getOrderId()).isEqualTo(savedOrderId);
    }

    @Test
    void updateOrderTest() {
        int savedOrderId = ordersRepository.saveOrder();
        Orders readOrder = ordersRepository.readOrderByOrderId(savedOrderId).get();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = sdf.format(new Timestamp(System.currentTimeMillis()));
        readOrder.setOrderDateTime(now);
        ordersRepository.updateOrder(readOrder);
        Orders updatedOrder = ordersRepository.readOrderByOrderId(savedOrderId).get();

        Assertions.assertThat(updatedOrder.getOrderDateTime()).isEqualTo(now);
    }

    @Test
    void readAllOrder() {
        ordersRepository.saveOrder();
        ordersRepository.saveOrder();

        List<Orders> orders = ordersRepository.readAllOrder();
        Assertions.assertThat(orders.size()).isEqualTo(2);
    }
}
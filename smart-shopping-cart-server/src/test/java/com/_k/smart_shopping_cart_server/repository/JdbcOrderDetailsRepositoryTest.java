package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.OrderDetails;
import com._k.smart_shopping_cart_server.domain.Products;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class JdbcOrderDetailsRepositoryTest {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    void orderDetailsMethodTest() {
        int savedProduct1Id = productsRepository.saveProduct(new Products("1", "상품A", 1000, 10, "A1"));
        int savedOrder1Id = ordersRepository.saveOrder();
        int savedOrderDetail1Id = orderDetailsRepository.saveOrderDetail(new OrderDetails(savedOrder1Id, savedProduct1Id, 1));

        OrderDetails readOrderDetail1 = orderDetailsRepository.readOrderDetailByOrderDetailId(savedOrderDetail1Id).get();
        Assertions.assertThat(readOrderDetail1.getOrderDetailId()).isEqualTo(savedOrderDetail1Id);

        List<OrderDetails> orderDetails = orderDetailsRepository.readAllOrderDetail();
        Assertions.assertThat(orderDetails.size()).isEqualTo(1);

        int savedProduct2Id = productsRepository.saveProduct(new Products("2", "상품B", 2000, 20, "A2"));
        int savedProduct3Id = productsRepository.saveProduct(new Products("3", "상품C", 3000, 30, "A3"));
        int savedOrder2Id = ordersRepository.saveOrder();
        int savedOrderDetail2Id = orderDetailsRepository.saveOrderDetail(new OrderDetails(savedOrder1Id, savedProduct2Id, 2));
        int savedOrderDetail3Id = orderDetailsRepository.saveOrderDetail(new OrderDetails(savedOrder2Id, savedProduct3Id, 3));

        List<OrderDetails> orderDetailsListByOrderId1 = orderDetailsRepository.readOrderDetailListByOrderId(savedOrder1Id);
        Assertions.assertThat(orderDetailsListByOrderId1.size()).isEqualTo(2);

        List<OrderDetails> orderDetailsListByOrderId2 = orderDetailsRepository.readOrderDetailListByOrderId(savedOrder2Id);
        Assertions.assertThat(orderDetailsListByOrderId2.size()).isEqualTo(1);
    }
}
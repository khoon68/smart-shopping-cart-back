package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.OrderDetails;
import com._k.smart_shopping_cart_server.domain.Products;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderDetailsServiceImplTest {
    @Autowired
    ProductsService productsService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Test
    void orderDetailsServiceMethodTest() {
        int product1Id = productsService.generateProduct(new Products("barcode1", "상품A", 1000, 10, "A1"));
        int product2Id = productsService.generateProduct(new Products("barcode2", "상품B", 2000, 20, "B2"));
        int order1Id = ordersService.generateOrder();
        int orderDetail1Id = orderDetailsService.generateOrderDetail(new OrderDetails(order1Id, product1Id, 5));
        OrderDetails orderDetail1 = orderDetailsService.showOrderDetailByOrderDetailId(orderDetail1Id);

        Assertions.assertThat(orderDetail1.getProductId()).isEqualTo(product1Id);

        int orderDetail2Id = orderDetailsService.generateOrderDetail(new OrderDetails(order1Id, product2Id, 10));
        List<OrderDetails> orderDetails = orderDetailsService.showOrderDetailListByDetailId(order1Id);
        Assertions.assertThat(orderDetails.size()).isEqualTo(2);
    }
}
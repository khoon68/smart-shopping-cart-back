package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Carts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CartsServiceImplTest {
    @Autowired
    CartService cartService;

    @Autowired
    OrdersService ordersService;

    @Test
    void cartServiceMethodTest() {
        String cart1Id = cartService.generateCart(new Carts("1"));
        Carts cart1 = cartService.showCartById(cart1Id);
        int order1Id = ordersService.generateOrder();

        cartService.changeOrderId(cart1, order1Id);
        Carts changedCart1 = cartService.showCartById(cart1Id);

        Assertions.assertThat(changedCart1.getOrderId()).isEqualTo(order1Id);
    }
}
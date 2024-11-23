package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Carts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JdbcCartsRepositoryTest {
    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void insertAndReadCartTest() {
        String cartId = cartsRepository.saveCart(new Carts("1111"));
        Carts readCart = cartsRepository.readCartByCartId(cartId).get();
        int order1Id = ordersRepository.saveOrder();
        readCart.setOrderId(order1Id);

        Assertions.assertThat(readCart.getId()).isEqualTo(cartId);
        Assertions.assertThat(readCart.getOrderId()).isEqualTo(order1Id);
    }

    @Test
    void updateCartTest() {
        String cartId = cartsRepository.saveCart(new Carts("1111"));
        Carts readCart = cartsRepository.readCartByCartId(cartId).get();

        int order2Id = ordersRepository.saveOrder();
        readCart.setOrderId(order2Id);
        cartsRepository.updateOrderId(readCart);
        Carts readCart2 = cartsRepository.readCartByCartId(cartId).get();

        Assertions.assertThat(readCart2.getOrderId()).isEqualTo(order2Id);
    }
}
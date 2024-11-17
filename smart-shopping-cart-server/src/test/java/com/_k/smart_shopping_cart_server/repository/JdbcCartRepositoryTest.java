package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Cart;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcCartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void insertAndReadCartTest() {
        String cartId = cartRepository.saveCart(new Cart("1111"));
        Cart readCart = cartRepository.readCartByCartId(cartId).get();
        int order1Id = ordersRepository.saveOrder();
        readCart.setOrderId(order1Id);

        Assertions.assertThat(readCart.getCartId()).isEqualTo(cartId);
        Assertions.assertThat(readCart.getOrderId()).isEqualTo(order1Id);
    }

    @Test
    void updateCartTest() {
        String cartId = cartRepository.saveCart(new Cart("1111"));
        Cart readCart = cartRepository.readCartByCartId(cartId).get();

        int order2Id = ordersRepository.saveOrder();
        readCart.setOrderId(order2Id);
        cartRepository.updateOrderId(readCart);
        Cart readCart2 = cartRepository.readCartByCartId(cartId).get();

        Assertions.assertThat(readCart2.getOrderId()).isEqualTo(order2Id);
    }
}
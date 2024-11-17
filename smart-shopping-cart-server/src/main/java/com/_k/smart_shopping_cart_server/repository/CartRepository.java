package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    String saveCart(Cart cart);
    Optional<Cart> readCartByCartId(String cartId);
    List<Cart> readAllCart();
    String updateOrderId(Cart cart);
    void deleteAllCart();
}

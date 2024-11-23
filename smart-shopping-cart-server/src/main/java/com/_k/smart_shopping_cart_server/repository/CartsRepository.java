package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Carts;

import java.util.List;
import java.util.Optional;

public interface CartsRepository {
    String saveCart(Carts cart);
    Optional<Carts> readCartByCartId(String id);
    List<Carts> readAllCart();
    String updateOrderId(Carts cart);
    void deleteAllCart();
}

package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Carts;

public interface CartService {
    String generateCart(Carts cart);
    Carts showCartById(String cartId);
    String changeOrderId(Carts cart, int orderId);
}

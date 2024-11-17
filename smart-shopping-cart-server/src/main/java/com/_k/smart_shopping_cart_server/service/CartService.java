package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Cart;

public interface CartService {
    String generateCart(Cart cart);
    Cart showCartById(String cartId);
    String changeOrderId(Cart cart, int orderId);
}

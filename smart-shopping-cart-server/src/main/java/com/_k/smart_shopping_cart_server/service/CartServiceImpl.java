package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Cart;
import com._k.smart_shopping_cart_server.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public String generateCart(Cart cart) {
        return cartRepository.saveCart(cart);
    }

    @Override
    public Cart showCartById(String cartId) {
        Optional<Cart> cartNullable = cartRepository.readCartByCartId(cartId);
        if (cartNullable.isEmpty()) throw new IllegalStateException(cartId + "에 해당하는 카트는 없습니다.");
        return cartNullable.get();
    }

    @Override
    public String changeOrderId(Cart cart, int orderId) {
        cart.setOrderId(orderId);
        return cartRepository.updateOrderId(cart);
    }
}

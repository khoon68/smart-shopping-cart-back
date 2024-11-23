package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Carts;
import com._k.smart_shopping_cart_server.repository.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartsRepository cartsRepository;

    @Override
    public String generateCart(Carts cart) {
        return cartsRepository.saveCart(cart);
    }

    @Override
    public Carts showCartById(String cartId) {
        Optional<Carts> cartNullable = cartsRepository.readCartByCartId(cartId);
        if (cartNullable.isEmpty()) throw new IllegalStateException(cartId + "에 해당하는 카트는 없습니다.");
        return cartNullable.get();
    }

    @Override
    public String changeOrderId(Carts cart, int orderId) {
        cart.setOrderId(orderId);
        return cartsRepository.updateOrderId(cart);
    }
}

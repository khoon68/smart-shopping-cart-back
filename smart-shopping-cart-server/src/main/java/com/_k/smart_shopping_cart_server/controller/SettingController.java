package com._k.smart_shopping_cart_server.controller;

import com._k.smart_shopping_cart_server.domain.Cart;
import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.dto.ResponseWrapper;
import com._k.smart_shopping_cart_server.repository.CartRepository;
import com._k.smart_shopping_cart_server.repository.OrderDetailsRepository;
import com._k.smart_shopping_cart_server.repository.OrdersRepository;
import com._k.smart_shopping_cart_server.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/setting")
public class SettingController {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<ResponseWrapper> settingDB() {
        ResponseWrapper<Object> resWrapper = new ResponseWrapper<>();

        orderDetailsRepository.deleteAllOrderDetail();
        cartRepository.deleteAllCart();
        ordersRepository.deleteAllOrder();
        productsRepository.deleteAllProduct();

        cartRepository.saveCart(new Cart("cartId"));
        productsRepository.saveProduct(new Products("barcode1", "상품A", 1000, 5, "1번 진열대"));
        productsRepository.saveProduct(new Products("barcode2", "상품B", 2000, 10, "2번 진열대"));

        return new ResponseEntity<>(resWrapper, HttpStatus.OK);
    }
}

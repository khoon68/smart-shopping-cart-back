package com._k.smart_shopping_cart_server.controller;

import com._k.smart_shopping_cart_server.domain.Carts;
import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.dto.ResponseWrapper;
import com._k.smart_shopping_cart_server.repository.CartsRepository;
import com._k.smart_shopping_cart_server.repository.OrderDetailsRepository;
import com._k.smart_shopping_cart_server.repository.OrdersRepository;
import com._k.smart_shopping_cart_server.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    CartsRepository cartsRepository;

    @PostMapping("/")
    public ResponseEntity<ResponseWrapper> settingDB() {
        ResponseWrapper<Object> resWrapper = new ResponseWrapper<>();

        try {
            orderDetailsRepository.deleteAllOrderDetail();
            cartsRepository.deleteAllCart();
            ordersRepository.deleteAllOrder();
            productsRepository.deleteAllProduct();

            cartsRepository.saveCart(new Carts("4bafc367"));
            productsRepository.saveProduct(new Products("8801117283001", "상품A", 1000, 5, "1번 진열대"));
            productsRepository.saveProduct(new Products("8806197630929", "상품B", 2000, 10, "2번 진열대"));
            productsRepository.saveProduct(new Products("8806197630950", "상품C", 3000, 15, "3번 진열대"));

            return new ResponseEntity<>(resWrapper, HttpStatus.OK);
        } catch (RuntimeException err) {
            resWrapper.setMessage(err.getMessage());
            return new ResponseEntity<>(resWrapper, HttpStatus.CONFLICT);
        }
    }
}

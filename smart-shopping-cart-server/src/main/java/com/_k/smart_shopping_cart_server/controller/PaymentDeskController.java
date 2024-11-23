package com._k.smart_shopping_cart_server.controller;

import com._k.smart_shopping_cart_server.domain.Carts;
import com._k.smart_shopping_cart_server.domain.OrderDetails;
import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.dto.ResponseWrapper;
import com._k.smart_shopping_cart_server.service.CartService;
import com._k.smart_shopping_cart_server.service.OrderDetailsService;
import com._k.smart_shopping_cart_server.service.OrdersService;
import com._k.smart_shopping_cart_server.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paymentDesk")
public class PaymentDeskController {
    @Autowired
    ProductsService productsService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailsService orderDetailsService;
    @Autowired
    CartService cartService;

    @GetMapping("/scan/{cartId}")
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> showCartOrderInfo(
            @PathVariable("cartId") String cartId
    ) {
        ResponseWrapper<Map<String, Object>> resWrapper = new ResponseWrapper<>();
        Map<String, Object> orderData = new HashMap<>();
        List<Map<String, Object>> detailList = new ArrayList<>();

        try {
            Carts cart = cartService.showCartById(cartId);
            List<OrderDetails> orderDetailList = orderDetailsService.showOrderDetailListByOrderId(cart.getOrderId());

            orderData.put("orderId", cart.getOrderId());

            for(OrderDetails orderDetail: orderDetailList) {
                Map<String, Object> detail = new HashMap<>();
                Products product = productsService.showProductInfoById(orderDetail.getProductId());
                detail.put("productName", product.getName());
                detail.put("productPrice", product.getPrice());
                detail.put("quantity", orderDetail.getQuantity());
                detailList.add(detail);
            }
            orderData.put("orderTime", ordersService.fillInOrder(cart.getOrderId()));
            orderData.put("orderDetailList", orderDetailList);
            resWrapper.setData(orderData);
            return new ResponseEntity<>(resWrapper, HttpStatus.OK);
        } catch(RuntimeException e) {
            resWrapper.setMessage(e.getMessage());
            return new ResponseEntity<>(resWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/payment/{orderId}")
    public ResponseEntity<ResponseWrapper> showPaymentStatus(
            @PathVariable("orderId") int orderId
    ) {
        ResponseWrapper resWrapper = new ResponseWrapper<>();

        try {
            List<OrderDetails> orderDetailList = orderDetailsService.showOrderDetailListByOrderId(orderId);
            for(OrderDetails orderDetail: orderDetailList) {
                Products product = productsService.showProductInfoById(orderDetail.getProductId());
                productsService.changeProductQuantity(product.getId(), orderDetail.getQuantity());
            }

            return new ResponseEntity<>(resWrapper, HttpStatus.ACCEPTED);
        } catch (RuntimeException err) {
            resWrapper.setMessage(err.getMessage());
            return new ResponseEntity<>(resWrapper, HttpStatus.BAD_REQUEST);
        }

    }
}

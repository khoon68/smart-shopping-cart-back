package com._k.smart_shopping_cart_server.controller;

import com._k.smart_shopping_cart_server.domain.Cart;
import com._k.smart_shopping_cart_server.domain.OrderDetails;
import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.dto.CartItemForm;
import com._k.smart_shopping_cart_server.dto.CartItemListForm;
import com._k.smart_shopping_cart_server.dto.ResponseWrapper;
import com._k.smart_shopping_cart_server.service.CartService;
import com._k.smart_shopping_cart_server.service.OrderDetailsService;
import com._k.smart_shopping_cart_server.service.OrdersService;
import com._k.smart_shopping_cart_server.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    ProductsService productsService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    CartService cartService;

    @GetMapping("/productList")
    public ResponseEntity<ResponseWrapper<List<Products>>> showAllProduct() {
        ResponseWrapper<List<Products>> resWrapper = new ResponseWrapper<>();

        try {
            List<Products> productList = productsService.showAllProducts();
            resWrapper.setData(productList);
            return new ResponseEntity<>(resWrapper, HttpStatus.OK);
        } catch (RuntimeException err) {
            resWrapper.setMessage(err.getMessage());
            return new ResponseEntity<>(resWrapper, HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/purchase")
    public ResponseEntity<ResponseWrapper> addOrder(@RequestBody CartItemListForm cartItemListForm) {
        ResponseWrapper resWrapper = new ResponseWrapper();
        try {
            String cartId = cartItemListForm.getCartId();
            List<CartItemForm> cartItemFormList = cartItemListForm.getCartItemFormList();

            int orderId = ordersService.generateOrder();
            cartService.changeOrderId(cartService.showCartById(cartId), orderId);

            for(CartItemForm cartItemForm: cartItemFormList) {
                int productId = cartItemForm.getProductId();
                int productQuantity = cartItemForm.getProductQuantity();
                OrderDetails orderDetail = new OrderDetails(orderId, productId, productQuantity);
                orderDetailsService.generateOrderDetail(orderDetail);
            }
            return new ResponseEntity<>(resWrapper, HttpStatus.ACCEPTED);
        } catch (RuntimeException err) {
            resWrapper.setMessage(err.getMessage());
            return new ResponseEntity<>(resWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{barcode}")
    public ResponseEntity<ResponseWrapper<Products>> showProductInfo(@PathVariable("barcode") String barcode) {
        ResponseWrapper<Products> resWrapper = new ResponseWrapper<>();

        try {
            Products product = productsService.showProductInfoByBarcode(barcode);
            resWrapper.setData(product);
            return new ResponseEntity<>(resWrapper, HttpStatus.OK);
        } catch (RuntimeException err) {
            return new ResponseEntity<>(resWrapper, HttpStatus.BAD_REQUEST);
        }
    }
}

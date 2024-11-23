package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.repository.CartsRepository;
import com._k.smart_shopping_cart_server.repository.OrderDetailsRepository;
import com._k.smart_shopping_cart_server.repository.OrdersRepository;
import com._k.smart_shopping_cart_server.repository.ProductsRepository;
import com._k.smart_shopping_cart_server.service.ProductsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductsServiceImplTest {

    @Autowired
    private ProductsService productsService;

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    CartsRepository cartsRepository;

    @BeforeEach
    void beforeEach() {
        productsRepository.deleteAllProduct();
        ordersRepository.deleteAllOrder();
        orderDetailsRepository.deleteAllOrderDetail();
        cartsRepository.deleteAllCart();
        productsRepository.saveProduct(new Products("1", "상품A", 1000, 10, "1A"));
        productsRepository.saveProduct(new Products("2", "상품B", 2000, 20, "2B"));
    }

    @Test
    void showProductInfoByBarcodeAndById() {
        Products product1ByBarcode = productsService.showProductInfoByBarcode("1");
        Products product2ByBarcode = productsService.showProductInfoByBarcode("2");

        assertThat(product1ByBarcode.getName()).isEqualTo("상품A");
        assertThat(product2ByBarcode.getName()).isEqualTo("상품B");

        Products product1ById = productsService.showProductInfoById(product1ByBarcode.getId());
        Products product2ById = productsService.showProductInfoById(product2ByBarcode.getId());

        assertThat(product1ById.getName()).isEqualTo("상품A");
        assertThat(product2ById.getName()).isEqualTo("상품B");
    }

    @Test
    void changeProductQuantity() {
        Products product1 = productsService.showProductInfoByBarcode("1");
        productsService.changeProductQuantity(product1.getId(), 5);

        assertThat(productsService.showProductInfoById(product1.getId()).getQuantity()).isEqualTo(5);
        assertThatThrownBy(() -> productsService.changeProductQuantity(product1.getId(), 10)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void showAllProducts() {
        List<Products> products = productsService.showAllProducts();
        assertThat(products.size()).isEqualTo(2);
    }
}
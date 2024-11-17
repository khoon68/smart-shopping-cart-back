package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Products;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class JdbcProductsRepositoryTest {

    @Autowired
    ProductsRepository productsRepository;

    @BeforeEach
    void beforeEach() {
        productsRepository.saveProduct(new Products("1", "A", 1000, 5, "1번 진열장"));
        productsRepository.saveProduct(new Products("2", "B", 2000, 10, "2번 진열장"));
    }

    @Test
    void readAllProduct() {
        List<Products> products = productsRepository.readAllProduct();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void readProductByBarcode() {
        Optional<Products> product1 = productsRepository.readProductByBarcode("1");
        product1.ifPresent((product) -> {
            Assertions.assertThat(product.getName()).isEqualTo("A");
        });
    }

    @Test
    void updateProductQuantity() {
        Products product1 = productsRepository.readProductByBarcode("1").get();
        product1.setQuantity(3);
        productsRepository.updateProductQuantity(product1);

        Products updatedProduct1 = productsRepository.readProductByBarcode("1").get();
        Assertions.assertThat(updatedProduct1.getQuantity()).isEqualTo(3);
    }
}
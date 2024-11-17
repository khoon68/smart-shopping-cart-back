package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    int saveProduct(Products product);
    Optional<Products> readProductById(int productId);
    Optional<Products> readProductByBarcode(String barcode);
    int updateProductQuantity(Products product);
    List<Products> readAllProduct();
    void deleteAllProduct();
}

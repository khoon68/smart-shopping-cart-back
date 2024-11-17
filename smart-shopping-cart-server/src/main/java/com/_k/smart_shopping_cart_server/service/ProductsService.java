package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Products;

import java.util.List;

public interface ProductsService {
    int generateProduct(Products products);
    Products showProductInfoById(int productId);
    Products showProductInfoByBarcode(String barcode);
    int changeProductQuantity(int productId, int quantity);
    List<Products> showAllProducts();
}

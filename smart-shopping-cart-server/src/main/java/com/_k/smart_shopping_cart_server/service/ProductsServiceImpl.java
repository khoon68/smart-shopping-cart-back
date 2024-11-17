package com._k.smart_shopping_cart_server.service;

import com._k.smart_shopping_cart_server.domain.Products;
import com._k.smart_shopping_cart_server.repository.ProductsRepository;
import com._k.smart_shopping_cart_server.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public int generateProduct(Products product) {
        return productsRepository.saveProduct(product);
    }

    @Override
    public Products showProductInfoById(int productId) {
        Optional<Products> productNullable = productsRepository.readProductById(productId);
        if (productNullable.isEmpty()) throw new IllegalStateException(productId + "에 해당하는 상품이 없습니다.");

        return productNullable.get();
    }

    @Override
    public Products showProductInfoByBarcode(String barcode) {
        Optional<Products> productNullable = productsRepository.readProductByBarcode(barcode);
        if (productNullable.isEmpty()) throw new IllegalStateException(barcode + "에 해당하는 상품이 없습니다.");

        return productNullable.get();
    }

    @Override
    public int changeProductQuantity(int productId, int quantity) {
        Optional<Products> productNullable = productsRepository.readProductById(productId);
        if (productNullable.isEmpty()) throw new IllegalStateException(productId + "에 해당하는 상품이 없습니다.");

        Products product = productNullable.get();
        int currentProductQuantity = product.getQuantity();
        int changedQuantity = currentProductQuantity - quantity;
        if (changedQuantity < 0) throw new IllegalStateException("상품의 재고 수량은 0보다 적을 수 없습니다.");

        product.setQuantity(changedQuantity);
        return productsRepository.updateProductQuantity(product);
    }

    @Override
    public List<Products> showAllProducts() {
        return productsRepository.readAllProduct();
    }
}

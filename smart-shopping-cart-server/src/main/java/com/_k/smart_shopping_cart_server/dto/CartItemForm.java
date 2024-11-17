package com._k.smart_shopping_cart_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemForm {
    private int productId;
    private int productQuantity;

    public CartItemForm() {}

    public CartItemForm(int productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

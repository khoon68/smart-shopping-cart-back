package com._k.smart_shopping_cart_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Products {
    private int id;
    private String barcode;
    private String name;
    private int price;
    @Setter
    private int quantity;
    private String location;

    public Products() {}

    public Products(
            String barcode, String name, int price, int quantity, String location
    ) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
    }

    public Products(int id, String barcode, String name, int price, int quantity, String location) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
    }
}

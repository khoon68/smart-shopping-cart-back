package com._k.smart_shopping_cart_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {
    private String message;
    private T data;
}

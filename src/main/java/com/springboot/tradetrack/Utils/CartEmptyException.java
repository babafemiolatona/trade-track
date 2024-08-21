package com.springboot.tradetrack.Utils;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(String message) {
        super(message);
    }
}

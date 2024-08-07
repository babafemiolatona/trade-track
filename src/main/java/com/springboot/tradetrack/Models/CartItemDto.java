package com.springboot.tradetrack.Models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {

    public CartItemDto(CartItem cartItem) {
        //TODO Auto-generated constructor stub
    }
    private Integer id;
    private ProductDto product;
    private int quantity;
    private BigDecimal subTotal;

    // public CartItemDto(CartItem cartItem) {
    //     this.id = cartItem.getId();
    //     this.product = new ProductDto(cartItem.getProduct());
    //     this.quantity = cartItem.getQuantity();
    //     this.subtotal = cartItem.getSubTotal();
    // }

}

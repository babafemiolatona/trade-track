package com.springboot.tradetrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Models.CustomUserDetails;
import com.springboot.tradetrack.Models.Order;
import com.springboot.tradetrack.Service.CartService;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("add/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Integer productId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return cartService.addToCart(userId, productId);
    }

    @PostMapping("create-order")
    public ResponseEntity<Order> createOrderFromCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return cartService.createOrderFromCart(userId);
    }

    @DeleteMapping("remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer productId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return cartService.removeFromCart(userId, productId);        
    }

    @DeleteMapping("clear")
    public ResponseEntity<String> clearCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return cartService.clearCart(userId);
    }
}

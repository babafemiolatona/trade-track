package com.springboot.tradetrack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Cart;
import com.springboot.tradetrack.Models.CartItem;
import com.springboot.tradetrack.Models.Product;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {

    CartItem findByCartAndProduct(Cart cart, Product product);

}

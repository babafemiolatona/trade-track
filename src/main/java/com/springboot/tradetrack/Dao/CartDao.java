package com.springboot.tradetrack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Cart;

public interface CartDao  extends JpaRepository<Cart, Integer> {

    Cart findByUserId(Integer userId);

}

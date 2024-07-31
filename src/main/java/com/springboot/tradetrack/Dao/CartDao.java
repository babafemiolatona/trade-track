package com.springboot.tradetrack.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Cart;

public interface CartDao  extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(Integer userId);

}

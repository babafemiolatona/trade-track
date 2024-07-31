package com.springboot.tradetrack.Models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;


@Data
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private LocalDateTime orderDate;
    private List<Integer> productIds;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    } 
}

package com.springboot.tradetrack.Models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductUpdate {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
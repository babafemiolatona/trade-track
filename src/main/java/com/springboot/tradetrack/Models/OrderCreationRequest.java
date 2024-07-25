package com.springboot.tradetrack.Models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderCreationRequest {

    // private Integer userId;
    private LocalDateTime orderDate;
    private List<Integer> productIds;
}

package com.springboot.tradetrack.Models;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

}
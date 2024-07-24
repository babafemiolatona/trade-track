package com.springboot.tradetrack.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Integer userId; // Custom field

    // Constructor
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer userId) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.userId = userId;
    }

    // Getters and setters for all fields
    // Implement all methods from UserDetails interface

    public Integer getUserId() {
        return userId;
    }

    
    // Other getters and UserDetails methods implementation
}
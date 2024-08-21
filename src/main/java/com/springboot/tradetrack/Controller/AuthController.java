package com.springboot.tradetrack.Controller;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Service.AuthService;

import jakarta.validation.Valid;

import com.springboot.tradetrack.Models.AuthRequest;
import com.springboot.tradetrack.Models.RegisterRequest;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) throws Exception {
        return authService.login(authRequest);
    }
}
package com.springboot.tradetrack.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Models.CustomUserDetails;
import com.springboot.tradetrack.Models.Order;
import com.springboot.tradetrack.Models.OrderCreationRequest;
// import com.springboot.tradetrack.Models.OrderRequest;
import com.springboot.tradetrack.Service.OrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreationRequest orderRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {

        Integer userId = userDetails.getUserId();
        // System.out.println("User id: " + userId);
        System.out.println("OrderController.createOrder - User id: " + userId);


        return orderService.createOrder(orderRequest, userId);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }
}

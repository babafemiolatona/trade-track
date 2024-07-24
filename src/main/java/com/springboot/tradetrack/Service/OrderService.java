package com.springboot.tradetrack.Service;

import java.util.List;
import java.util.Optional;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.OrderDao;
import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Dao.UserDao;
import com.springboot.tradetrack.Models.Order;
import com.springboot.tradetrack.Models.OrderCreationRequest;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    // public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
    //     Order order = convertToOrder(orderRequest);
    //     orderDao.save(order);
    //     return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    // }

    public ResponseEntity<Order> createOrder(OrderCreationRequest orderRequest, Integer userId) {
        Order order = new Order();
        order.setUserId(userId, userDao);
        // System.out.println(userId);
        order.setOrderDate(orderRequest.getOrderDate());
        order.setProductIds(orderRequest.getProductIds(), productDao);

        Order savedOrder = orderDao.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Order>> getOrdersByUserId(Integer userId) {
        List<Order> orders = orderDao.findByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Order>> getOrderById(Integer orderId) {
        Optional<Order> order = orderDao.findById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // private Order convertToOrder(OrderRequest orderRequest) {
    //     Order order = new Order();
    //     order.setUserId(orderRequest.getUserId(), userDao);

    //     // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    //     // LocalDateTime orderDate = LocalDateTime.parse(orderRequest.getOrderDate(), formatter);
    //     order.setOrderDate(orderRequest.getOrderDate());

    //     order.setProductIds(orderRequest.getProductIds(), productDao);
    //     return order;
    // }
}

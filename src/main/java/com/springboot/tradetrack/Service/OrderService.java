package com.springboot.tradetrack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.tradetrack.Dao.OrderDao;
import com.springboot.tradetrack.Models.Order;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderDao.save(order);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Order> getOrder(Integer id) {
        return new ResponseEntity<>(orderDao.findById(id).get(), HttpStatus.OK);
    }

    
}

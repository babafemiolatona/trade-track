package com.springboot.tradetrack.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.CartDao;
import com.springboot.tradetrack.Dao.OrderDao;
import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Dao.UserDao;
import com.springboot.tradetrack.Models.Cart;
import com.springboot.tradetrack.Models.CartItem;
import com.springboot.tradetrack.Models.Order;
import com.springboot.tradetrack.Models.OrderItem;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    CartDao cartDao;
    // public ResponseEntity<Order> createOrder(OrderCreationRequest orderRequest, Integer userId) {        
    //     Order order = new Order();
    //     order.setUserId(userId, userDao);
    //     order.setOrderDate(LocalDateTime.now());
    //     order.setProductIds(orderRequest.getProductIds(), productDao);

    //     Order savedOrder = orderDao.save(order);
    //     return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    // }

    public ResponseEntity<Order> createOrderFromCart(Integer userId) {
        Cart cart = cartDao.findByUserId(userId);

        if (cart == null || cart.getItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order order = new Order();
        order.setUserId(userId, userDao);
        order.setOrderDate(LocalDateTime.now());

        Set<OrderItem> orderItems = new HashSet<>();
        BigDecimal orderTotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubTotal(cartItem.getSubTotal());
            orderItem.setOrder(order);
            
            orderItems.add(orderItem);
            orderTotal = orderTotal.add(cartItem.getSubTotal());
        }

        order.setOrderItems(orderItems);
        order.setTotalSubTotal(orderTotal);

        Order savedOrder = orderDao.save(order);

        cart.getItems().clear();
        cartDao.save(cart);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Order>> getUserOrders(Integer userId) {
        List<Order> orders = orderDao.findByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Optional<Order>> getOrderById(Integer orderId) {
        Optional<Order> order = orderDao.findById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

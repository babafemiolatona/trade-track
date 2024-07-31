package com.springboot.tradetrack.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.CartDao;
import com.springboot.tradetrack.Dao.OrderDao;
import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Dao.UserDao;
import com.springboot.tradetrack.Models.Cart;
import com.springboot.tradetrack.Models.Order;
import com.springboot.tradetrack.Models.Product;
import com.springboot.tradetrack.Models.User;

@Service
public class CartService {

    @Autowired
    CartDao cartDao;

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    public ResponseEntity<String> addToCart(Integer userId, Integer productId) {
        Optional<Cart> optionalCart = findCartByUserId(userId);
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            User user = userDao.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            cart = new Cart();
            cart.setUser(user);
        }

        cart.getProducts().add(new Product(productId));
        cartDao.save(cart);

        return new ResponseEntity<>("Product added to cart", HttpStatus.OK);

    }

    public Optional<Cart> findCartByUserId(Integer userId) {
        return cartDao.findByUserId(userId);
    }

    public ResponseEntity<String> removeFromCart(Integer userId, Integer productId) {
        Optional<Cart> optionalCart = findCartByUserId(userId);
        
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            boolean removed = cart.getProducts().removeIf(product -> product.getId().equals(productId));
            
            if (removed) {
                cartDao.save(cart);
                return new ResponseEntity<>("Product removed from cart", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found in cart", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Cart not found for user", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> clearCart(Integer userId) {
        Optional<Cart> optionalCart = findCartByUserId(userId);
    
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getProducts().clear();
            cartDao.save(cart);
            return new ResponseEntity<>("Cart cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart not found for user", HttpStatus.NOT_FOUND);
        }
    }

     public ResponseEntity<Order> createOrderFromCart(Integer userId) {
        Optional<Cart> optionalCart = cartDao.findByUserId(userId);

        if (!optionalCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart cart = optionalCart.get();
        Order order = new Order();
        order.setUserId(userId, userDao);
        order.setOrderDate(LocalDateTime.now());
        Set<Product> productSet = new HashSet<>(cart.getProducts());
        order.setProducts(productSet);

        Order savedOrder = orderDao.save(order);

        cart.getProducts().clear();
        cartDao.save(cart);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
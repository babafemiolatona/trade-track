package com.springboot.tradetrack.Service;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.CartDao;
import com.springboot.tradetrack.Dao.CartItemDao;
import com.springboot.tradetrack.Dao.OrderDao;
import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Dao.UserDao;
import com.springboot.tradetrack.Models.Cart;
import com.springboot.tradetrack.Models.CartItem;
import com.springboot.tradetrack.Models.Product;
import com.springboot.tradetrack.Utils.CartEmptyException;

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

    @Autowired
    CartItemDao cartItemDao;

    public ResponseEntity<String> addToCart(Integer userId, Integer productId, Integer quantity) {
        Cart cart = cartDao.findByUserId(userId);
        Product product = productDao.findById(productId).orElse(null);
        
        Optional<CartItem> existingCartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst();

        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);

            BigDecimal itemSubTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            cartItem.setSubTotal(itemSubTotal);

            cart.getItems().add(cartItem);
        }

        cartItemDao.save(cartItem);
        cartDao.save(cart);
        
        return new ResponseEntity<>("Product added to cart", HttpStatus.CREATED);
    }

    public Cart getCartByUserId(Integer userId) {
        Cart cart = cartDao.findByUserId(userId);

        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }
        BigDecimal Total = calculateTotal(cart);
        cart.setTotal(Total);

        return cart;
    }

    public ResponseEntity<String> removeFromCart(Integer userId, Integer productId) {
        Cart cart = getCartByUserId(userId);
        
        if (cart != null) {
            boolean removed = cart.getItems().removeIf(cartItem -> cartItem.getId().equals(productId));
            
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
        Cart cart = getCartByUserId(userId);
    
        if (cart != null) {
            cart.getItems().clear();
            cartDao.save(cart);
            return new ResponseEntity<>("Cart cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart not found for user", HttpStatus.NOT_FOUND);
        }
    }

    public BigDecimal calculateTotal(Cart cart) {
        List<CartItem> items = cart.getItems();
        return items.stream()
                    .map(CartItem::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}
package com.springboot.tradetrack.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Dao.UserDao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    private LocalDateTime orderDate;

    @ManyToMany
    @JsonManagedReference
    private Set<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Integer userId, UserDao userDao) {
        this.user = userDao.findById(userId).orElse(null);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setProductIds(List<Integer> productIds, ProductDao productDao) {
        this.products = productIds.stream()
                                  .map(productDao::findById)
                                  .filter(Optional::isPresent)
                                  .map(Optional::get)
                                  .collect(Collectors.toSet());
    }
}
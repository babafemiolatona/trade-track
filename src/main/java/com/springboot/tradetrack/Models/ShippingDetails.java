package com.springboot.tradetrack.Models;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.tradetrack.Dao.UserDao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // @JsonBackReference
    @JsonIgnore
    @ToStringExclude
    private User user;

    public void setUserId(Integer userId, UserDao userDao) {
        this.user = userDao.findById(userId).orElse(null);
    }
}

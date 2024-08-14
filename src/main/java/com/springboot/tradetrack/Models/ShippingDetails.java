package com.springboot.tradetrack.Models;

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
    private User user;
}

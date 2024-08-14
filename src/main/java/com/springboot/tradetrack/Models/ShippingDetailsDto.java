package com.springboot.tradetrack.Models;

import lombok.Data;

@Data
public class ShippingDetailsDto {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phoneNumber;

}

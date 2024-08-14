package com.springboot.tradetrack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.ShippingDetailsDao;
import com.springboot.tradetrack.Models.ShippingDetails;
import com.springboot.tradetrack.Models.ShippingDetailsDto;

import jakarta.transaction.Transactional;

@Service
public class ShippingDetailsService {

    @Autowired
    ShippingDetailsDao shippingDetailsDao;

    @Transactional
    public ResponseEntity<String> saveShippingDetails(ShippingDetailsDto shippingDetailsDto) {

        System.out.println("Received Shipping Details: " + shippingDetailsDto);

        if (shippingDetailsDto == null) {
            return new ResponseEntity<>("Shipping Details cannot be null", HttpStatus.BAD_REQUEST);
        }

        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setFirstName(shippingDetailsDto.getFirstName());
        shippingDetails.setLastName(shippingDetailsDto.getLastName());
        shippingDetails.setAddress(shippingDetailsDto.getAddress());
        shippingDetails.setCity(shippingDetailsDto.getCity());
        shippingDetails.setState(shippingDetailsDto.getState());
        shippingDetails.setCountry(shippingDetailsDto.getCountry());
        shippingDetails.setZipCode(shippingDetailsDto.getZipCode());
        shippingDetails.setPhoneNumber(shippingDetailsDto.getPhoneNumber());

        try {
            shippingDetailsDao.save(shippingDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving shipping details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Shipping Details saved successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<ShippingDetails> getShippingDetails(Integer id) {
        ShippingDetails shippingDetails = shippingDetailsDao.findById(id).orElseThrow(() -> new RuntimeException("Shipping details not found"));
        return new ResponseEntity<>(shippingDetails, HttpStatus.OK);
    }

    public ResponseEntity<String> updateShippingDetails(ShippingDetailsDto shippingDetailsDto, Integer id) {
        try {
            if (shippingDetailsDao.existsById(id)) {
                ShippingDetails shippingDetails = shippingDetailsDao.findById(id).orElse(null);

                if (shippingDetails == null) {
                    return new ResponseEntity<>("Shipping details not found", HttpStatus.NOT_FOUND);
                }

                shippingDetails.setFirstName(shippingDetailsDto.getFirstName());
                shippingDetails.setLastName(shippingDetailsDto.getLastName());
                shippingDetails.setAddress(shippingDetailsDto.getAddress());
                shippingDetails.setCity(shippingDetailsDto.getCity());
                shippingDetails.setState(shippingDetailsDto.getState());
                shippingDetails.setCountry(shippingDetailsDto.getCountry());
                shippingDetails.setZipCode(shippingDetailsDto.getZipCode());
                shippingDetails.setPhoneNumber(shippingDetailsDto.getPhoneNumber());

                shippingDetailsDao.save(shippingDetails);
                return new ResponseEntity<>("Shipping details updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Shipping details not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating shipping details", HttpStatus.NOT_FOUND);
        }
    }

}

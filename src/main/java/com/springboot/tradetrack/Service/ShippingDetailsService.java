package com.springboot.tradetrack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.ShippingDetailsDao;
import com.springboot.tradetrack.Dao.UserDao;
import com.springboot.tradetrack.Models.ShippingDetails;
import com.springboot.tradetrack.Models.ShippingDetailsDto;
import jakarta.transaction.Transactional;

@Service
public class ShippingDetailsService {

    @Autowired
    ShippingDetailsDao shippingDetailsDao;

    @Autowired
    UserDao userDao;

    @Transactional
    public ResponseEntity<String> saveShippingDetails(ShippingDetailsDto shippingDetailsDto, Integer userId) {

        if (shippingDetailsDto == null) {
            return new ResponseEntity<>("Shipping Details cannot be null", HttpStatus.BAD_REQUEST);
        }

        // User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setFirstName(shippingDetailsDto.getFirstName());
        shippingDetails.setLastName(shippingDetailsDto.getLastName());
        shippingDetails.setAddress(shippingDetailsDto.getAddress());
        shippingDetails.setCity(shippingDetailsDto.getCity());
        shippingDetails.setState(shippingDetailsDto.getState());
        shippingDetails.setCountry(shippingDetailsDto.getCountry());
        shippingDetails.setZipCode(shippingDetailsDto.getZipCode());
        shippingDetails.setPhoneNumber(shippingDetailsDto.getPhoneNumber());

        shippingDetails.setUserId(userId, userDao);

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

    public ResponseEntity<List<ShippingDetails>> getShippingDetailsByUserId(Integer userId) {
        List<ShippingDetails> shippingDetails = shippingDetailsDao.findByUserId(userId);
        return new ResponseEntity<>(shippingDetails, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteShippingDetails(Integer id) {
        shippingDetailsDao.deleteById(id);
        return new ResponseEntity<>("Shipping details deleted successfully", HttpStatus.OK);
    }

    // public ResponseEntity<List<ShippingDetails>> getShippingDetailsByUserId(Integer userId) {
    //     List<ShippingDetails> shippingDetails = shippingDetailsDao.findByUserId(userId);
    //     if (shippingDetails.isEmpty()) {
    //         System.out.println("No shipping details found for userId: {}" + userId);
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     System.out.println("Shipping details retrieved successfully for userId: {}" + userId);
    //     return new ResponseEntity<>(shippingDetails, HttpStatus.OK);
    // }

}

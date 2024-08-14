package com.springboot.tradetrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Models.ShippingDetails;
import com.springboot.tradetrack.Models.ShippingDetailsDto;
import com.springboot.tradetrack.Service.ShippingDetailsService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/shipping-details")
public class ShippingDetailsController {

    @Autowired
    ShippingDetailsService shippingDetailsService;

    @PostMapping
    public ResponseEntity<String> saveShippingDetails(@RequestBody ShippingDetailsDto shippingDetailsDto) {
        return shippingDetailsService.saveShippingDetails(shippingDetailsDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ShippingDetails> getShippingDetails(@PathVariable Integer id) {
        return shippingDetailsService.getShippingDetails(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateShippingDetails(@RequestBody ShippingDetailsDto shippingDetailsDto, @PathVariable Integer id) {
        return shippingDetailsService.updateShippingDetails(shippingDetailsDto, id);
    }

}

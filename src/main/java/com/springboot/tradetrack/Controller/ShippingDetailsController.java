package com.springboot.tradetrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Models.CustomUserDetails;
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

    @PostMapping("add")
    public ResponseEntity<String> saveShippingDetails(@RequestBody ShippingDetailsDto shippingDetailsDto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return shippingDetailsService.saveShippingDetails(shippingDetailsDto, userId);
    }

    @GetMapping("{id}")
    public ResponseEntity<ShippingDetails> getShippingDetails(@PathVariable Integer id) {
        return shippingDetailsService.getShippingDetails(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateShippingDetails(@RequestBody ShippingDetailsDto shippingDetailsDto, @PathVariable Integer id) {
        return shippingDetailsService.updateShippingDetails(shippingDetailsDto, id);
    }

    @GetMapping
    public ResponseEntity<List<ShippingDetails>> getShippingDetailsByUserId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer userId = userDetails.getUserId();
        return shippingDetailsService.getShippingDetailsByUserId(userId);
    }
}

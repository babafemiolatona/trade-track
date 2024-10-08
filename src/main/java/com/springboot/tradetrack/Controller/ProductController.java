package com.springboot.tradetrack.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tradetrack.Models.Product;
import com.springboot.tradetrack.Models.ProductDto;
import com.springboot.tradetrack.Service.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto, Integer id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }
}

package com.springboot.tradetrack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Models.Product;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDao.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<String> addProduct(Product product) {
        productDao.save(product);
        return new ResponseEntity<>("Product added Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Product> getProduct(Integer id) {
        Product product = productDao.findById(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(Product product, Integer id) {
        try {
            if (!id.equals(product.getId())) {
                return new ResponseEntity<>("Product id mismatch", HttpStatus.BAD_REQUEST);
            }
            if (productDao.existsById(id)) {
                product.setId(id);
                productDao.save(product);
                return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating product", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteProduct(Integer id) {
        productDao.deleteById(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }    
}

package com.springboot.tradetrack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.tradetrack.Dao.ProductDao;
import com.springboot.tradetrack.Models.Product;
import com.springboot.tradetrack.Models.ProductDto;
import com.springboot.tradetrack.Models.ProductUpdate;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDao.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<String> addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        productDao.save(product);
        return new ResponseEntity<>("Product added Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Product> getProduct(Integer id) {
        Product product = productDao.findById(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(ProductUpdate productUpdate, Integer id) {
        try {
            if (!id.equals(productUpdate.getId())) {
                return new ResponseEntity<>("Product id mismatch", HttpStatus.BAD_REQUEST);
            }
            if (productDao.existsById(id)) {
                Product product = productDao.findById(id).orElse(null);
                if (product == null) {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                }

                product.setName(productUpdate.getName());
                product.setPrice(productUpdate.getPrice());
                product.setDescription(productUpdate.getDescription());

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

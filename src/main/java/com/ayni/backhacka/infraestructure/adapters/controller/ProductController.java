package com.ayni.backhacka.infraestructure.adapters.controller;

import com.ayni.backhacka.application.service.ProductService;
import com.ayni.backhacka.domain.model.Product;
import com.ayni.backhacka.infraestructure.adapters.dto.ProductRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(
                request.getName(),
                request.getQuantity(),
                request.getUnitOfMeasure(),
                request.getCost(),
                request.getPrice(),
                request.getActiveIngredient(),
                request.getCategory()
        );
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {
        Optional<Product> updatedProduct = productService.updateProduct(id, request);

        return updatedProduct
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        Optional<Product> deletedProduct = productService.deleteProduct(id);

        return deletedProduct
                .map(product -> ResponseEntity.ok("Product deleted successfully"))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/bulk-add")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.addProducts(products);
        return ResponseEntity.ok(savedProducts);
    }
}
package com.ayni.backhacka.application.service;

import com.ayni.backhacka.domain.model.Product;
import com.ayni.backhacka.infraestructure.adapters.dto.ProductRequest;
import com.ayni.backhacka.infraestructure.adapters.ports.out.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Crear un producto y calcular totales
    public Product createProduct(String name, int quantity, String unitOfMeasure, double cost, double price, String category, String ingredientActive) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setUnitOfMeasure(unitOfMeasure);
        product.setCost(cost);
        product.setPrice(price);
        product.setActiveIngredient(ingredientActive);
        product.setCategory(category);
        product.setDateRegister(new Date());
        calculateTotals(product);

        return productRepository.save(product);
    }

    // Método para actualizar un producto
    public Optional<Product> updateProduct(String id, ProductRequest request) {
        return productRepository.findById(id).map(product -> {
            if (request.getName() != null) product.setName(request.getName());
            if (request.getQuantity() != 0) product.setQuantity(request.getQuantity());
            if (request.getUnitOfMeasure() != null) product.setUnitOfMeasure(request.getUnitOfMeasure());
            if (request.getActiveIngredient() != null) product.setActiveIngredient(request.getActiveIngredient());
            if (request.getCost() != 0) product.setCost(request.getCost());
            if (request.getPrice() != 0) product.setPrice(request.getPrice());
            if (request.getCategory() != null) product.setCategory(request.getCategory());

            calculateTotals(product);
            return productRepository.save(product);
        });
    }

    // Método para eliminar un producto
    public Optional<Product> deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
        return product;
    }

    // Método para calcular los totales
    private void calculateTotals(Product product) {
        product.setTotalPrice(product.getQuantity() * product.getPrice());
        product.setTotalCost(product.getQuantity() * product.getCost());
        product.setTotalProfit((product.getPrice() - product.getCost()) * product.getQuantity());
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> addProducts(List<Product> products) {
        products.forEach(product -> {
            if (product.getDateRegister() == null) {
                product.setDateRegister(new Date()); // Solo asigna la fecha actual si no está en el JSON
            }
        });
        return productRepository.saveAll(products);
    }

}


package com.ayni.backhacka.infraestructure.adapters.ports.out;

import com.ayni.backhacka.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}


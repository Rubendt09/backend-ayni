package com.ayni.backhacka.infraestructure.adapters.ports.out;

import com.ayni.backhacka.domain.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<Sale, String> {

}
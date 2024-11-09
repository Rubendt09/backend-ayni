package com.ayni.backhacka.infraestructure.adapters.ports.out;

import com.ayni.backhacka.domain.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
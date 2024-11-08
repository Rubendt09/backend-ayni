package com.ayni.backhacka.infraestructure.adapters.ports.out;

import com.ayni.backhacka.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}

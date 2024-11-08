package com.ayni.backhacka.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id private String id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String cellphone;
    private int age;
    private String dni;
}

package com.ayni.backhacka.infraestructure.adapters.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class LoginDTO {
    @Getter @Setter
    @NotEmpty(message = "Email is required")
    private String email;

    @Getter @Setter
    @NotEmpty(message = "Password is required")
    private String password;

    public  LoginDTO(){}

    public  LoginDTO(String email, String password){
        this.email = email;
        this.password = password;
    }
}

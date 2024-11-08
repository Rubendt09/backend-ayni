package com.ayni.backhacka.infraestructure.adapters.dto;

import com.ayni.backhacka.domain.model.User;
import lombok.Getter;
import lombok.Setter;

public class AuthResponse {
    @Getter
    @Setter
    private User user;
    @Getter @Setter private String token;

    public AuthResponse(User user, String token){
        this.user = user;
        this.token = token;
    }
}
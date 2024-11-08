package com.ayni.backhacka.infraestructure.adapters.controller;

import com.ayni.backhacka.application.service.UserService;
import com.ayni.backhacka.domain.model.User;
import com.ayni.backhacka.infraestructure.adapters.dto.AuthResponse;
import com.ayni.backhacka.infraestructure.adapters.dto.LoginDTO;
import com.ayni.backhacka.infraestructure.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired private UserService userService;
    @Autowired JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
        try {
            User newuser = userService.registerUser(user);
            return new ResponseEntity<>(newuser, HttpStatus.CREATED); // Retornar 201 CREATED si el registro es exitoso
        } catch (IllegalArgumentException e) {
            // Si el email ya existe, devolver un 400 BAD REQUEST con el mensaje de error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDTO loginDTO){
        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null){
            String token = jwtTokenProvider.generateToken(user.getEmail());
            user.setPassword(null);
            return ResponseEntity.ok(new AuthResponse(user, token));
        } return  ResponseEntity.status(401).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @Valid @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK); // Retornar 200 OK si la actualización es exitosa
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

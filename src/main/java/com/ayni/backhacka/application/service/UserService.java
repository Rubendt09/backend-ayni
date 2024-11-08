package com.ayni.backhacka.application.service;

import com.ayni.backhacka.domain.model.User;
import com.ayni.backhacka.infraestructure.adapters.ports.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //Register user
    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        User newUser = new User();
        newUser.setRuc(user.getRuc());
        newUser.setCompanyName(user.getCompanyName());
        newUser.setEmail(user.getEmail());
        newUser.setCellphone(user.getCellphone());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }

    public  User login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user != null && bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }return null;

    }

    // Actualización de usuario
    public User updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Solo actualizamos los campos permitidos
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setCellphone(updatedUser.getCellphone());

        // No se modifica ni DNI ni edad

        return userRepository.save(existingUser);
    }
}

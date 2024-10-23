package edu.eci.cvds.TaskManager.service;

import edu.eci.cvds.TaskManager.model.User;
import edu.eci.cvds.TaskManager.repository.UserRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro de usuario sin encriptación
    public User registerUser(String username, String email, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            System.out.println("Usuario encontrado: " + existingUser.get().getUsername());
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    // Autenticación de usuario sin encriptación
    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        
        // Verificamos si la contraseña en texto plano coincide con la encriptada
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta.");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

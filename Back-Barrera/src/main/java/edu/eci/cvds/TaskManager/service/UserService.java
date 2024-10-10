package edu.eci.cvds.TaskManager.service;

import edu.eci.cvds.TaskManager.model.User;
import edu.eci.cvds.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registro de usuario sin encriptación
    public User registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    // Autenticación de usuario sin encriptación
    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        // Comparar la contraseña directamente
        if (!user.getPassword().equals(rawPassword)) {
            throw new RuntimeException("Contraseña incorrecta.");
        }
        return user;
    }
}

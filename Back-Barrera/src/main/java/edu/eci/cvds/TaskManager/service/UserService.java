package edu.eci.cvds.TaskManager.service;

import edu.eci.cvds.TaskManager.model.User;
import edu.eci.cvds.TaskManager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro de usuario
    public User registerUser(String username, String email, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            System.out.println("Usuario encontrado: " + existingUser.get().getUsername());
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }
        User user = new User(username,email,password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Cifrar contraseña
        user.addRole("ROLE_USER"); // Asignar rol USER por defecto
        return userRepository.save(user);
    }

    // Autenticación de usuario
    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Verificar la contraseña
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta.");
        }
        return user;
    }

    // Crear usuario admin
    public void createAdminUser() {
        User adminUser = new User("admin","admin@example.com","admin123");
        adminUser.addRole("ROLE_ADMIN"); // Asignar rol ADMIN
        adminUser.addRole("ROLE_USER"); // Asignar rol USER también
        saveUser(adminUser); // Guardar usuario admin
    }

    // Guardar usuario
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Cifrar la contraseña
        userRepository.save(user); // Guardar en la base de datos
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return UserDetailsImpl.build(user); // Utiliza tu clase UserDetailsImpl para construir el objeto UserDetails
    }
}

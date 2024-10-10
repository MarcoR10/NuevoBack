package edu.eci.cvds.TaskManager.controller;

import edu.eci.cvds.TaskManager.model.User;
import edu.eci.cvds.TaskManager.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")  // Ajusta esto dependiendo de tu frontend
public class AuthController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
            return ResponseEntity.ok(newUser); // Respuesta 200 OK con el usuario creado
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Enviar mensaje de error en caso de fallo
        }
    }

   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData, HttpSession session) {
    String username = loginData.get("username");
    String password = loginData.get("password");
    User authenticatedUser = userService.authenticate(username, password); // Comparación sin encriptación
    session.setAttribute("user", authenticatedUser); // Guardar el usuario en la sesión
    return ResponseEntity.ok("Login exitoso");
}

}

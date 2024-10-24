package edu.eci.cvds.TaskManager.controller;

import edu.eci.cvds.TaskManager.model.User;
import edu.eci.cvds.TaskManager.security.TokenUtils;
import edu.eci.cvds.TaskManager.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
            return ResponseEntity.ok(newUser); // Respuesta 200 OK con el usuario creado
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Enviar mensaje de error en caso de fallo
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println("Aqui no esta el error1");
        try {
            System.out.println("Intento de login con: " + username + " y contraseña: " + password);

            // Autenticación del usuario
            User authenticatedUser = userService.authenticate(username, password);
            System.out.println("Aqui no está el error2");
            // Si la autenticación es exitosa, generar el token JWT
            if (authenticatedUser != null) {
                System.out.println("Aqui no está el error3");
                String token = TokenUtils.createToken(authenticatedUser.getUsername(), authenticatedUser.getEmail());
                System.out.println("Aqui no está el error4");
                System.out.println("Token generado: " + token);
                // Devolver el token en la respuesta
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("message", "Login exitoso");
                System.out.println("Aqui no está el error5");
                return ResponseEntity.ok(response);
            } else {
                
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            System.out.println("Si hubo error :(");
            return ResponseEntity.status(500).body("Error del servidor: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> logout(HttpSession session) {
        try {
            session.invalidate(); // Invalida la sesión actual
            return ResponseEntity.ok("Sesión cerrada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cerrar la sesión: " + e.getMessage());
        }
    }


    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    

}

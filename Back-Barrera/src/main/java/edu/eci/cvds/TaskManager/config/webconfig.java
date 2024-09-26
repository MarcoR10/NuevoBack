package edu.eci.cvds.TaskManager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8081")  // Cambia esto según la URL de tu front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").
                allowedHeaders("*");
    }
}

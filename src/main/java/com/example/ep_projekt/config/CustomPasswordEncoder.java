package com.example.ep_projekt.config;

// Importerar nödvändiga klasser för Spring konfiguration och lösenordshantering
import org.springframework.context.annotation.Bean; // För att definiera en Spring Bean
import org.springframework.context.annotation.Configuration; // För att definiera en konfigurationsklass
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // För att använda BCrypt för lösenordshantering
import org.springframework.security.crypto.password.PasswordEncoder; // Interface som definierar lösenordshantering


// Märker klassen som en konfigurationsklass för Spring
@Configuration
public class CustomPasswordEncoder {
    // Definierar en Bean för PasswordEncoder som använder BCryptPasswordEncoder
    @Bean
    public PasswordEncoder bcryptPasswordEncoder () {

        return new BCryptPasswordEncoder(); // Returnerar en instans av BCryptPasswordEncoder som implementerar PasswordEncoder
    }
}

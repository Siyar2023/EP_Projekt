package com.example.ep_projekt.dao;

// Importerar modellen CustomUser som representerar användarobjektet
import com.example.ep_projekt.model.CustomUser;

import java.util.List; // Importerar List för att hantera flera användare
import java.util.Optional; // Importerar Optional för att hantera null-värden på ett säkert sätt

// Definierar ett gränssnitt för användardatabasoperationer
public interface IUserDAO {

    // Metod för att hämta en användare baserat på användarnamn
    // returnerar en Optional för att hantera fallet där användaren inte finns
    Optional<CustomUser> findByUsername(String username);

    // Metod för att spara en CustomUser i databasen
    void save(CustomUser customUser);

    // Metod för att hämta alla användare från databasen som en lista
    List<CustomUser> findAll();

    // Metod för att hämta en användare baserat på dess unika ID
    // returnerar en Optional för att hantera fallet där användaren inte finns
    Optional<CustomUser> findById(Long id);

    // Metod för att radera en användare baserat på dess ID
    void deleteById(Long id);
}

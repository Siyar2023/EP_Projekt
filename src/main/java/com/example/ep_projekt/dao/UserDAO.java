package com.example.ep_projekt.dao;

// Importerar de nödvändiga klasserna och gränssnitten
import com.example.ep_projekt.model.CustomUser; // Användarens modellklass
import com.example.ep_projekt.repository.UserRepository; // Repository-klass för databasoperationer
import org.springframework.beans.factory.annotation.Autowired; // För att injicera beroenden automatiskt
import org.springframework.stereotype.Component; // För att markera klassen som en Spring-komponent

import java.util.List; // För att hantera listor med användare
import java.util.Optional; // För att hantera objekt som kan vara null på ett säkert sätt

// Markera denna klass som en Spring-komponent som hanterar användardatabasoperationer
@Component
public class UserDAO implements IUserDAO {

    // Skapa en instansvariabel som refererar till UserRepository
    private final UserRepository userRepository;

    // Konstruktor som injicerar UserRepository-instansen
    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository; // Tilldelar den injicerade repository-instansen till variabeln
    }

    // Hämtar en användare baserat på användarnamn
    @Override
    public Optional<CustomUser> findByUsername(String username) {
        return userRepository.findByUsername(username); // Använder repository för att hitta användaren
    }

    // Sparar en användare i databasen
    @Override
    public void save(CustomUser customUser) {
        userRepository.save(customUser); // Använder repository för att spara användaren i databasen
    }

    // Hämtar alla användare från databasen
    @Override
    public List<CustomUser> findAll() {
        return userRepository.findAll(); // Hämtar alla användare via repository
    }

    // Hämtar en användare baserat på användarens ID
    @Override
    public Optional<CustomUser> findById(Long id) {
        return userRepository.findById(id); // Hämtar användaren baserat på ID
    }

    // Raderar en användare baserat på ID
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id); // Tar bort användaren via repository baserat på ID
    }
}

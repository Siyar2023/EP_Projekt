package com.example.ep_projekt.dao;

// Importerar nödvändiga klasser och gränssnitt
import com.example.ep_projekt.model.FilmModel; // Representerar filmens modell i applikationen
import com.example.ep_projekt.repository.FilmRepository; // Hanterar databasoperationer för FilmModel
import org.springframework.beans.factory.annotation.Autowired; // För automatisk beroendehantering
import org.springframework.stereotype.Component; // Markerar denna klass som en Spring-komponent som kan hanteras av Spring Container

import java.util.List; // Används för att hantera listor av filmer
import java.util.Optional; // Hanterar objekt som kan vara null på ett säkert sätt

// Markerar klassen som en komponent så att den kan användas i andra delar av Spring-applikationen
@Component
public class FilmDAO implements IFilmDAO {

    // Skapar en instansvariabel för att referera till repository-lagret
    private final FilmRepository filmRepository;

    // Konstruktor för att injicera en instans av FilmRepository med hjälp av @Autowired
    @Autowired
    public FilmDAO(FilmRepository filmRepository) {
        this.filmRepository = filmRepository; // Tilldelar repository-instansen till den lokala variabeln
    }

    // Sparar en ny film eller uppdaterar en befintlig film i databasen
    @Override
    public FilmModel save(FilmModel filmModel) {
        return filmRepository.save(filmModel); // Använder repository för att spara objektet i databasen
    }

    // Hämtar alla filmer från databasen som en lista
    @Override
    public List<FilmModel> findAll() {
        return filmRepository.findAll(); // Använder repository för att hämta alla filmobjekt
    }

    // Hämtar en film baserat på dess titel
    @Override
    public Optional<FilmModel> findByTitle(String filmName) {
        return filmRepository.findByTitle(filmName); // Returnerar en Optional med filmen om den hittas
    }

    // Hämtar en film baserat på dess unika ID
    @Override
    public Optional<FilmModel> findById(Integer filmId) {
        return filmRepository.findById(filmId); // Använder repository för att hitta filmen med angivet ID
    }

    // Raderar en film från databasen baserat på dess ID
    @Override
    public void deleteById(Integer filmId) {
        filmRepository.deleteById(filmId); // Använder repository för att ta bort filmen
    }

    // Hämtar en film där titeln matchar, oavsett skillnad mellan stora och små bokstäver
    @Override
    public Optional<FilmModel> findByTitleIgnoreCase(String filmName) {
        return filmRepository.findByTitleIgnoreCase(filmName); // Ignorerar skiftläge vid sökning
    }

    // Hämtar alla filmer där titeln innehåller ett visst sökord, oavsett skillnad mellan stora och små bokstäver
    @Override
    public List<FilmModel> findByTitleContainingIgnoreCase(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title); // Söker efter titlar som delvis matchar sökordet
    }
}

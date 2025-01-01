package com.example.ep_projekt.dao;

// Importerar FilmModel som representerar filmen i applikationen
import com.example.ep_projekt.model.FilmModel;
// Importerar Response för hantering av respons i applikationen (kan vara för framtida användning)
import com.example.ep_projekt.response.Response;
// Importerar ResponseEntity från Spring, som används för att hantera HTTP-respons
import org.springframework.http.ResponseEntity;

// Importerar nödvändiga Java-klasser för att hantera I/O och samlingar
import java.io.IOException;
import java.util.List; // För att hantera listor av FilmModel-objekt
import java.util.Optional; // För att hantera värden som kan vara null på ett säkert sätt

// IFilmDAO är ett gränssnitt som definierar de metodsignaturer som FilmDAO-implementeringen ska följa
public interface IFilmDAO {

    // Metod för att spara en ny film eller uppdatera en befintlig film i databasen
    FilmModel save(FilmModel filmModel);

    // Metod för att hämta alla filmer från databasen som en lista
    List<FilmModel> findAll();

    // Metod för att hämta en film baserat på dess titel
    Optional<FilmModel> findByTitle(String filmName);

    // Metod för att hämta en film baserat på dess unika ID
    Optional<FilmModel> findById(Integer filmId);

    // Metod för att radera en film från databasen baserat på dess ID
    void deleteById(Integer filmId);

    // Metod för att hämta en film där titeln matchar, utan att beakta versal och gemen
    Optional<FilmModel> findByTitleIgnoreCase(String filmName);

    // Metod för att hämta alla filmer där titeln innehåller ett visst sökord, utan att beakta versal och gemen
    List<FilmModel> findByTitleContainingIgnoreCase(String title);
}

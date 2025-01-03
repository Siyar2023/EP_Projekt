package com.example.ep_projekt.model;


// Importerar nödvändiga klasser från Jakarta Persistence-paketet.
// Detta används för att hantera persistens i Java-applikationer.
import jakarta.persistence.*;

// Markerar klassen som en "embeddable" enhet, vilket innebär att den kan inkluderas som en komponent i andra entiteter.
// Klassen kan inte existera som en fristående entitet i databasen.
@Embeddable
public class Language {


    private Long id;

    // Fält för att lagra det engelska namnet på språket.
    private String englishName;

    // Fält för att lagra ISO 639-1-koden för språket.
    private String iso639_1;

    // Fält för att lagra det lokala namnet på språket.
    private String name;

    // Getter-metod för att hämta värdet av `id`.
    public Long getId() {
        return id;
    }

    // Setter-metod för att sätta värdet av `id`.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter-metod för att hämta det engelska namnet på språket.
    public String getEnglishName() {
        return englishName;
    }

    // Setter-metod för att sätta det engelska namnet på språket.
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    // Getter-metod för att hämta ISO 639-1-koden för språket.
    public String getIso639_1() {
        return iso639_1;
    }

    // Setter-metod för att sätta ISO 639-1-koden för språket.
    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    // Getter-metod för att hämta det lokala namnet på språket.
    public String getName() {
        return name;
    }

    // Setter-metod för att sätta det lokala namnet på språket.
    public void setName(String name) {
        this.name = name;
    }
}

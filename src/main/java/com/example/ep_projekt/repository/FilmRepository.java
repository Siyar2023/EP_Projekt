package com.example.ep_projekt.repository;

import com.example.ep_projekt.model.FilmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono; // Reactivt programmeringsstöd (används ej här)
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FilmRepository extends JpaRepository<FilmModel, Integer> {

    /**
     * Hittar en film baserat på dess exakta titel.
     * @param title Den exakta titeln på filmen.
     * @return En Optional som innehåller FilmModel om den hittas, annars tom.
     */
    Optional<FilmModel> findByTitle(String title);

    /**
     * Hittar en film baserat på dess titel, oberoende av versaler/gemener.
     * @param title Titeln på filmen att söka efter (skiftlägesokänslig).
     * @return En Optional som innehåller FilmModel om den hittas, annars tom.
     */
    Optional<FilmModel> findByTitleIgnoreCase(String title);

    /**
     * Hittar alla filmer vars titlar innehåller en viss delsträng, oberoende av versaler/gemener.
     * @param title Delsträngen att söka efter i filmtitlar (skiftlägesokänslig).
     * @return En lista med FilmModel-objekt som matchar sökkriterierna.
     */
    List<FilmModel> findByTitleContainingIgnoreCase(String title);
}

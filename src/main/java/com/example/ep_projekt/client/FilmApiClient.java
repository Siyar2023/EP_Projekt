package com.example.ep_projekt.client;

import com.example.ep_projekt.model.FilmModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

/**
 * FilmApiClient används för att interagera med The Movie Database (TMDb) API
 * och hämta data om filmer baserat på deras ID.
 */
@Component
public class FilmApiClient {

    // API-nyckel för autentisering vid anrop till TMDb API
    private String ApiKey = "df0eb7f0729911f3781785d3811ec8dd";

    // WebClient används för att skapa HTTP-anrop
    private final WebClient webClient;

    /**
     * Konstruktor som initierar en WebClient-instans.
     *
     * @param webClientBuilder används för att bygga WebClient med bas-URL för TMDb API.
     */
    public FilmApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3/") // Bas-URL för TMDb API
                .build();
    }

    /**
     * Hämtar information om en film från TMDb API baserat på filmens ID.
     *
     * @param filmId ID för filmen som ska hämtas.
     * @return Optional med FilmModel-objekt om filmen hittas, annars en tom Optional.
     */
    public Optional<FilmModel> getFilmById(int filmId) {
        try {
            // Gör ett GET-anrop till TMDb API för att hämta filmdata
            FilmModel film = webClient.get()
                    .uri(uri -> uri
                            .path("movie/" + filmId) // Specifik endpoint för filmen
                            .queryParam("api_key", ApiKey) // Lägger till API-nyckeln som parameter
                            .build())
                    .retrieve() // Skickar begäran
                    .bodyToMono(FilmModel.class) // Konverterar svaret till en FilmModel
                    .block(); // Blockerar tills svaret mottas

            // Returnerar filmen om den finns
            return Optional.ofNullable(film);

        } catch (WebClientResponseException.NotFound e) {
            // Om filmen inte hittas (404) returneras en tom Optional
            return Optional.empty();
        } catch (WebClientResponseException e) {
            // Om andra HTTP-fel inträffar kastas undantaget vidare
            throw e;
        }
    }
}

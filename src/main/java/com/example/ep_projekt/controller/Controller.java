package com.example.ep_projekt.controller;

import com.example.ep_projekt.model.CustomUser;
import com.example.ep_projekt.model.FilmModel;
import com.example.ep_projekt.response.ErrorResponse;
import com.example.ep_projekt.response.ListResponse;
import com.example.ep_projekt.response.Response;
import com.example.ep_projekt.service.IFilmService;
import com.example.ep_projekt.service.IUserService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * REST-kontroller för hantering av filmer och relaterade operationer.
 */
@RestController
@RequestMapping("/films")
public class Controller {

    // API-nyckel för externa API-anrop
    private String ApiKey = "df0eb7f0729911f3781785d3811ec8dd";

    // Tjänster och klienter som används i kontrollerna
    private final IFilmService filmService;
    private final WebClient webClientConfig;
    private final RateLimiter rateLimiter;
    private final IUserService userService;

    /**
     * Konstruktor som initialiserar tjänster och WebClient.
     */
    public Controller(WebClient.Builder webClient, IFilmService filmService, RateLimiter rateLimiter, IUserService userService) {
        this.webClientConfig = webClient
                .baseUrl("https://api.themoviedb.org/3/")
                .build();
        this.filmService = filmService;
        this.rateLimiter = rateLimiter;
        this.userService = userService;
    }

    /**
     * Hämtar en film baserat på dess ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getFilmById(@RequestParam(defaultValue = "movie") String movie, @PathVariable int id) {
        return filmService.getFilmById(id);
    }

    /**
     * Sparar en film baserat på dess ID.
     */
    @PostMapping("/{id}")
    public ResponseEntity<Response> saveFilmById(@RequestParam(defaultValue = "movie") String movie, @PathVariable int id) throws IOException {
        return filmService.saveFilmById("movie", id);
    }

    /**
     * Hämtar alla sparade filmer.
     */
    @GetMapping("/savedfilms")
    public ResponseEntity<Response> getSavedFilms() {
        if (rateLimiter.acquirePermission()) {
            return ResponseEntity.ok(new ListResponse(filmService.findAll()));
        } else {
            return ResponseEntity.status(429).body(new ErrorResponse("för många förfrågan"));
        }
    }

    /**
     * Hämtar en sparad film baserat på dess ID.
     */
    @GetMapping("/savedfilms/{id}")
    public ResponseEntity<Response> getFilm(@PathVariable int id) {
        ResponseEntity<Response> film = filmService.findById(id);
        FilmModel film2 = (FilmModel) film.getBody();
        return ResponseEntity.ok(new ErrorResponse(film2.getTitle()));
    }

    /**
     * Ändrar ursprungslandet för en sparad film.
     */
    @PutMapping("/savedfilms/{id}")
    public ResponseEntity<Response> changeCountryOfOrigin(@PathVariable("id") int id, @RequestBody String country) {
        if (rateLimiter.acquirePermission()) {
            return filmService.changeCountryOfOrigin(id, country);
        } else {
            return ResponseEntity.status(429).body(new ErrorResponse("för många förfrågan"));
        }
    }

    /**
     * Lägger till en åsikt för en sparad film.
     */
    @PutMapping("/savedfilms/opinion/{id}")
    public ResponseEntity<String> addOpinion(@PathVariable("id") Integer id, @RequestBody String opinion) {
        if (rateLimiter.acquirePermission()) {
            return filmService.addOpinion(id, opinion);
        } else {
            return ResponseEntity.status(429).body("För många förfrågan");
        }
    }

    /**
     * Tar bort en sparad film baserat på dess ID.
     */
    @DeleteMapping("/savedfilms/{id}")
    public ResponseEntity<String> deleteFilmById(@PathVariable("id") Integer id) throws Exception {
        if (rateLimiter.acquirePermission()) {
            return filmService.deleteById(id);
        } else {
            return ResponseEntity.status(429).body("för många förfrågan");
        }
    }

    /**
     * Hämtar genomsnittlig speltid för sparade filmer.
     */
    @GetMapping("/savedfilms/runtime")
    public ResponseEntity<Response> getAverageRuntime() {
        if (rateLimiter.acquirePermission()) {
            return filmService.getAverageRuntime();
        } else {
            return ResponseEntity.status(429).body(new ErrorResponse("för många förfrågan"));
        }
    }

    /**
     * Söker efter en film baserat på dess titel.
     */
    @GetMapping("/search")
    public ResponseEntity<Response> searchByTitle(@RequestParam String filmName) {
        if (rateLimiter.acquirePermission()) {
            return filmService.searchFilmByName(filmName);
        } else {
            return ResponseEntity.status(429).body(new ErrorResponse("för många förfrågan"));
        }
    }

    /**
     * Hämtar filmer baserat på land och eventuell titel.
     */
    @GetMapping("/country/{country}")
    public ResponseEntity<Response> getFilmsByCountry(@PathVariable("country") String country,
                                                      @RequestParam(value = "title", required = false) String title) {
        return filmService.getFilmByCountry(country, title);
    }

    /**
     * Hämtar generell information om filmer.
     */
    @GetMapping("/info")
    public ResponseEntity<Response> getInfo() {
        return filmService.getInfo();
    }

    /**
     * Hämtar en film med ytterligare information som åsikter och beskrivning.
     */
    @GetMapping("/getfilm/{filmId}")
    public ResponseEntity<Response> getFilmWithAdditionalInfo(@PathVariable("filmId") int filmId,
                                                              @RequestParam(value = "opinion", defaultValue = "false") boolean opinion,
                                                              @RequestParam(value = "description", defaultValue = "false") boolean description) {
        return filmService.getFilmWithAdditionalInfo(filmId, opinion, description);
    }
}

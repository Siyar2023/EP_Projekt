package com.example.ep_projekt.model;

// Import av nödvändiga klasser från externa bibliotek
import com.example.ep_projekt.response.Response;  // Importerar ett anpassat Response-interface
import jakarta.persistence.*;  // Importerar JPA (Java Persistence API) för att arbeta med databasen

import java.util.List;  // Importerar List-klassen för att hantera listor av objekt

// Definierar en entitetsklass som ska mappas till en databas-tabell
@Entity
public class FilmModel implements Response {  // Klassen FilmModel implementerar Response-interface för att användas i API-respons

    // Primary key (ID) för FilmModel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID:n genereras automatiskt av databasen
    private int filmid;

    // Många-till-många relation mellan FilmModel och CustomUser
    @ManyToMany
    @JoinTable(
            name = "user_films",  // Tabellen som ska skapa relationen mellan filmer och användare
            joinColumns = @JoinColumn(name = "film_id"),  // Kolumnen för FilmModel ID i join-tabellen
            inverseJoinColumns = @JoinColumn(name = "user_id")  // Kolumnen för användar ID i join-tabellen
    )
    private List<CustomUser> customUsers;  // Lista med CustomUser objekt (användare som har filmer)

    // En-till-många relation mellan FilmModel och UserFilm
    @OneToMany(mappedBy = "filmModel", cascade = CascadeType.ALL)
    private List<UserFilm> userFilms;  // Lista med UserFilm objekt (filmer associerade med användare)

    // Getter och setter för userFilms
    public List<UserFilm> getUserFilms() {
        return userFilms;
    }

    public void setUserFilms(List<UserFilm> userFilms) {
        this.userFilms = userFilms;
    }

    // Getter och setter för customUsers
    public List<CustomUser> getCustomUsers() {
        return customUsers;
    }

    public void setCustomUsers(List<CustomUser> customUsers) {
        this.customUsers = customUsers;
    }

    // Flera fält som beskriver filmens data
    private boolean adult;  // Om filmen är för vuxna
    private String backdropPath;  // URL till filmens bakgrundsbild
    private String belongsToCollection;  // Om filmen tillhör en samling
    private int budget;  // Filmens budget

    @ElementCollection  // Anger att det är en samling av element (t.ex. en lista med Genre-objekt)
    private List<Genre> genres;  // Lista av genrer (Action, Drama, etc.)

    private String homepage;  // Filmen's officiella hemsida
    private int id;  // Unikt ID för filmen
    private String imdbId;  // IMDB-ID för filmen
    private String originalLanguage;  // Originalspråk för filmen
    private String original_title;  // Originaltitel på filmen
    private List<String> origin_country;  // Ursprungsland för filmen
    @Column(length = 1000)  // Anger att denna kolumn kan ha upp till 1000 tecken
    private String overview;  // En sammanfattning av filmen
    private double popularity;  // Filmens popularitet
    private String poster_path;  // URL till filmens affisch

    // Flera elementkollektioner för filmens produktionsdata
    @ElementCollection
    private List<ProductionCompany> production_companies;  // Lista med produktionsbolag som varit med och producerat filmen

    @ElementCollection
    private List<ProductionCountry> production_countries;  // Lista med länder som filmen har producerats i
    private String release_date;  // Releasedatum för filmen
    private long revenue;  // Inkomst från filmen
    private int runtime;  // Filmtid i minuter
    @ElementCollection
    private List<Language> spoken_languages;  // Lista över talade språk i filmen
    private String status;  // Status för filmen (släppt, planerad, etc.)
    private String tagline;  // Filmen's tagline (kort beskrivning eller slogan)
    private String title;  // Filmens titel
    private boolean video;  // Om filmen är en video (t.ex. DVD/Bluray)
    private double vote_average;  // Genomsnittligt betyg på filmen
    private int vote_count;  // Antal röster på filmen

    // Konstruktor för FilmModel
    public FilmModel () {}

    // Getter och setter för de olika fälten som definierar filmens egenskaper
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(String belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public List<Language> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<Language> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getOriginal_title () {
        return original_title;
    }

    public void setOriginal_title (String original_title) {
        this.original_title = original_title;
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public List<ProductionCountry> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountry> production_countries) {
        this.production_countries = production_countries;
    }
}

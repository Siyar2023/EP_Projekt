package com.example.ep_projekt.model;
import com.example.ep_projekt.authorities.UserRole; // Importerar UserRole (används för att hantera användarroller)
import jakarta.persistence.*; // Importerar JPA-annoteringar för att hantera entiteter i databasen
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Importerar SimpleGrantedAuthority för hantering av auktoriteter

import java.util.List; // Importerar List för att lagra listor av objekt

@Entity // Markerar denna klass som en entitet som ska hanteras av JPA (databas)
public class CustomUser {

    // Konstruktor för att skapa en användare med användarnamn och lösenord
    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Standardkonstruktor
    public CustomUser () {

    }

    @Id // Markerar id-kolumnen som primärnyckel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anger att id genereras automatiskt av databasen (auto-increment)
    private Long id;

    private String username; // Användarnamn för användaren
    private String password; // Lösenord för användaren

    @Enumerated(EnumType.STRING) // Anger att userRole ska sparas som en sträng i databasen
    private UserRole userRole; // Rollen för användaren

    @ManyToMany(mappedBy = "customUsers") // Relationen till FilmModel, där denna är den "passiva" sidan av relationen
    private List<FilmModel> filmList; // Lista av filmer som användaren har

    @OneToMany(mappedBy = "customUser", cascade = CascadeType.ALL) // Relationen till UserFilm, där denna är den "aktiva" sidan av relationen
    private List<UserFilm> userFilms; // Lista av användarens filmer (UserFilm är en hjälparklass)

    // Getter och Setter för userFilms
    public List<UserFilm> getUserFilms() {
        return userFilms;
    }

    public void setUserFilms(List<UserFilm> userFilms) {
        this.userFilms = userFilms;
    }

    // Fält för att hantera användarens kontostatus
    private boolean isAccountNonExpired; // Om kontot har gått ut
    private boolean isAccountNonLocked; // Om kontot är låst
    private boolean isCredentialNonExpired; // Om användarens referenser (t.ex. lösenord) har gått ut
    private boolean isEnabled; // Om användaren är aktiverad eller inte

    // Getter och Setter för id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter och Setter för username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter och Setter för password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter och Setter för filmList (filmer som användaren har)
    public List<FilmModel> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<FilmModel> filmList) {
        this.filmList = filmList;
    }

    // Getter för att hämta auktoriteter baserat på användarens roll
    public List<SimpleGrantedAuthority> getAuthorities () {
        return userRole.getAuthorities(); // Hämtar auktoriteter från UserRole
    }

    // Getter för att hämta användarens behörigheter baserat på deras roll
    public List<String> getPermissions () {
        return userRole.getPermission(); // Hämtar behörigheter från UserRole
    }

    // Getter och Setter för userRole (användarroll)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    // Getter och Setter för isAccountNonExpired (om kontot är utgånget eller inte)
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    // Getter och Setter för isAccountNonLocked (om kontot är låst eller inte)
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    // Getter och Setter för isCredentialNonExpired (om användarens referenser är utgångna eller inte)
    public boolean isCredentialNonExpired() {
        return isCredentialNonExpired;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        isCredentialNonExpired = credentialNonExpired;
    }

    // Getter och Setter för isEnabled (om användaren är aktiverad eller inte)
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

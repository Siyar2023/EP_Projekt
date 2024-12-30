package com.example.ep_projekt.config;

// Importerar nödvändiga klasser från Spring Security och andra paket.
import com.example.ep_projekt.model.CustomUser; // min egen modellklass för användaren.
import org.springframework.security.core.GrantedAuthority; // Representerar en säkerhetsroll eller auktoritet.
import org.springframework.security.core.userdetails.UserDetails; // Interface som representerar användarinformation.

import java.util.Collection; // Används för att hantera en samling av auktoriteter.

public class CustomUserDetails implements UserDetails {

    // Fält för att lagra användarinformation och säkerhetsstatus.
    private final String username; // Användarnamn för autentisering.
    private final String password; // Användarens lösenord.
    private final Collection<? extends GrantedAuthority> authorities; // Auktoriteter/roller för användaren.
    private final boolean isAccountNonExpired; // Indikerar om kontot är förfallet.
    private final boolean isAccountNonLocked; // Indikerar om kontot är låst.
    private final boolean isCredentialNonExpired; // Indikerar om autentiseringsuppgifterna har gått ut.
    private final boolean isEnabled; // Indikerar om kontot är aktiverat.

    // Konstruktor för att skapa ett CustomUserDetails-objekt med specifika fält.
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialNonExpired, boolean isEnabled) {
        this.username = username; // Sätter användarnamn.
        this.password = password; // Sätter lösenord.
        this.authorities = authorities; // Sätter användarens roller/auktoriteter.
        this.isAccountNonExpired = isAccountNonExpired; // Anger om kontot inte är förfallet.
        this.isAccountNonLocked = isAccountNonLocked; // Anger om kontot inte är låst.
        this.isCredentialNonExpired = isCredentialNonExpired; // Anger om autentiseringsuppgifterna inte är förfallna.
        this.isEnabled = isEnabled; // Anger om kontot är aktiverat.
    }

    // Konstruktor som tar ett CustomUser-objekt och mappar dess fält.
    public CustomUserDetails(CustomUser user) {
        this.username = user.getUsername(); // Hämtar användarnamn från CustomUser.
        this.password = user.getPassword(); // Hämtar lösenord från CustomUser.
        this.authorities = user.getAuthorities(); // Hämtar roller/auktoriteter från CustomUser.
        this.isAccountNonExpired = user.isAccountNonExpired(); // Hämtar kontots förfallostatus.
        this.isAccountNonLocked = user.isAccountNonLocked(); // Hämtar kontots låsstatus.
        this.isCredentialNonExpired = user.isCredentialNonExpired(); // Hämtar autentiseringsuppgifternas status.
        this.isEnabled = user.isEnabled(); // Hämtar kontots aktiveringsstatus.
    }

    // Implementering av UserDetails-metoder:

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returnerar användarens roller/auktoriteter.
        return authorities;
    }

    @Override
    public String getPassword() {
        // Returnerar användarens lösenord.
        return password;
    }

    @Override
    public String getUsername() {
        // Returnerar användarens användarnamn.
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Returnerar om kontot inte är förfallet.
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Returnerar om kontot inte är låst.
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Returnerar om autentiseringsuppgifterna inte är förfallna.
        return isCredentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        // Returnerar om kontot är aktiverat.
        return isEnabled;
    }
}

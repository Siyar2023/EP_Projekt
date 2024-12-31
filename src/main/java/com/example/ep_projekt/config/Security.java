package com.example.ep_projekt.config;

import com.example.ep_projekt.authorities.UserRole;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.stereotype.Component;
import io.github.resilience4j.ratelimiter.RateLimiter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Markerar klassen som en konfigurationsklass för Spring.
// Detta innebär att den innehåller metoder som definierar beans som hanteras av Spring Container.
@Configuration
public class Security {

    // En tjänst som används för att hämta användardetaljer från databasen eller annan källa.
    private final CustomUserDetailService customUserDetailService;

    // Konstruktor för att injicera CustomUserDetailService via dependency injection.
    public Security(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    // Definierar en bean som hanterar säkerhetsfilterkedjan för webbapplikationen.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Konfigurerar åtkomstregler för olika URL-mönster.
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Tillåter alla användare att nå dessa sidor (login och logout).
                        .requestMatchers("https://localhost:8443/login", "/logout").permitAll()
                        .requestMatchers("/login", "/register").permitAll()
                        // Endast användare med ADMIN-roll kan nå dessa sidor.
                        .requestMatchers("/admin", "/delete/*").hasRole(UserRole.ADMIN.toString())
                        // Alla andra förfrågningar kräver autentisering.
                        .anyRequest().authenticated())

                // Konfigurerar inställningar för formulärinloggning.
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        // Anger en anpassad inloggningssida och tillåter alla att nå den.
                        .loginPage("/login").permitAll())

                // Konfigurerar inställningar för "Kom ihåg mig"-funktionen.
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                        // Parameter i formuläret som aktiverar "Kom ihåg mig".
                        .rememberMeParameter("remember-me")
                        // Token giltighetstid (här 20 dagar).
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(20))
                        // Nyckel för att generera token.
                        .key("appSecureKey")
                        // Använder customUserDetailService för att hämta användardetaljer.
                        .userDetailsService(customUserDetailService))

                // Konfigurerar utloggning.
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        // URL för utloggning.
                        .logoutUrl("/logout")
                        // Rensar autentiseringsinformationen.
                        .clearAuthentication(true)
                        // Ogiltigförklarar sessionen.
                        .invalidateHttpSession(true)
                        // Tar bort cookies.
                        .deleteCookies("remember-me", "JSESSIONID", "XSRF-TOKEN")
                        // Tillåter alla att nå utloggnings-URL:en.
                        .permitAll());

        // Returnerar den konfigurerade säkerhetskedjan.
        return http.build();
    }

    // Definierar en bean för att hantera hastighetsbegränsning (Rate Limiting).
    @Bean
    public RateLimiter rateLimiter() {
        // Konfigurerar en RateLimiter med max 1000 förfrågningar per 30 sekunder
        // och timeout på 5 sekunder om gränsen överskrids.
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1000)
                .limitRefreshPeriod(Duration.ofSeconds(30))
                .timeoutDuration(Duration.ofSeconds(5))
                .build();
        return RateLimiter.of("myRateLimiter", config);
    }

    // Definierar en bean för att ignorera säkerhetskontroller för vissa URL:er.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                // Ignorerar säkerhet för dessa URL-mönster (t.ex. H2-console och vissa API-endpoints).
                .requestMatchers("/h2-console/**", "/user/**", "/update/**", "/update/**");
    }
}
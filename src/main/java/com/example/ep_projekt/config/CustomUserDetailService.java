package com.example.ep_projekt.config;

// Importerar de klasser och paket som behövs för att använda Spring Security,
// skapa tjänster och hantera användaruppgifter.
import com.example.ep_projekt.model.CustomUser; // Modell för användardata (CustomUser är en representation av användaren i databasen).
import com.example.ep_projekt.repository.UserRepository; // Används för att interagera med databasen via ett repository.
import com.example.ep_projekt.service.IUserService; // Ett gränssnitt som definierar metoder för användarhantering.
import org.springframework.beans.factory.annotation.Autowired; // Används för att injicera beroenden.
import org.springframework.security.core.userdetails.UserDetails; // Gränssnitt som representerar säkerhetsdetaljer för en användare.
import org.springframework.security.core.userdetails.UserDetailsService; // Gränssnitt som används av Spring Security för att ladda användaruppgifter.
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Undantag som kastas om en användare inte hittas.
import org.springframework.stereotype.Service; // Markerar klassen som en "Service", vilket gör att Spring hanterar den som en komponent.




@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IUserService userService;  // Deklarerar ett fält för att hantera användartjänster.

    // Konstruktor för att injicera IUserService via @Autowired
    @Autowired
    public CustomUserDetailService (IUserService userService) {
        this.userService = userService; // Tilldelar den injicerade tjänsten till det lokala fältet.
    }

    // Implementerar metoden från UserDetailsService-gränssnittet.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hämtar en användare från tjänsten med hjälp av användarnamnet.
        // Metoden `findUserByUsername` returnerar en Optional<CustomUser>,
        // och `get()` används här för att hämta objektet.
        CustomUser user = userService.findUserByUsername(username).get();

        // Skapar ett nytt objekt av CustomUserDetails baserat på den hämtade användaren
        // och returnerar detta för att användas av Spring Security.
        return new CustomUserDetails(user);

    }
}
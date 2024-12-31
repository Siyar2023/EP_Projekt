package com.example.ep_projekt.controller;

import com.example.ep_projekt.authorities.UserRole;
import com.example.ep_projekt.model.CustomUser;
import com.example.ep_projekt.dto.UserDTO;
import com.example.ep_projekt.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    // Tjänster för användarhantering och lösenordskryptering
    private final IUserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(IUserService userService,
                          PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    // Endpoint för att visa inloggningssidan
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returnerar vy för inloggningssidan
    }

    // Endpoint för att hantera utloggning
    @GetMapping("/logout")
    public String logout() {
        return "logout"; // Returnerar vy för utloggningssidan
    }

    // Endpoint för att visa registreringssidan
    @GetMapping("/register")
    public String registerPage(Model model) {
        // Skapar en ny användare och lägger till roller i modellen
        model.addAttribute("user", new UserDTO("", "", UserRole.USER));
        model.addAttribute("roles", UserRole.values());
        return "register"; // Returnerar vy för registreringssidan
    }

    // Endpoint för att hantera registreringsformuläret
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult,
                               Model model) {
        // Validerar inmatade uppgifter
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", UserRole.values());
            return "register"; // Returnerar registreringssidan vid valideringsfel
        }

        // Sparar användaren och lägger till attribut för vyer
        model.addAttribute("status", userService.saveUser(userDTO));
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("user", new UserDTO("", "", UserRole.USER));

        return "register"; // Returnerar registreringssidan efter registrering
    }

    // Endpoint för att visa admin-sidan
    @GetMapping("/admin")
    public String adminPage(Model model) {
        // Hämtar alla användare och lägger till dem i modellen
        List<CustomUser> userList = userService.getAllUsers();
        model.addAttribute("users", userList);

        return "admin-page"; // Returnerar vy för admin-sidan
    }

    // Endpoint för att radera en användare
    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("id") Long id, Model model) {
        // Förhindrar radering av användare med id 1
        if (id == 1) {
            model.addAttribute("error", "kan inte radera användare med id: 1");
            model.addAttribute("users", userService.getAllUsers());
            return "admin-page"; // Returnerar admin-sidan med felmeddelande
        }

        // Raderar användaren med specificerat id
        userService.deleteUserById(id);

        return "redirect:/admin"; // Omdirigerar till admin-sidan
    }
}

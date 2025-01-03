package com.example.ep_projekt.dto;

// Importerar UserRole-klassen från ett specifikt paket.
// UserRole kan vara en enum eller klass som hanterar olika användarroller (t.ex., ADMIN, USER).
import com.example.ep_projekt.authorities.UserRole;

// Importerar valideringsanoteringar från Jakarta (en del av Java EE-specifikationen).
// Dessa används för att validera data i fält och säkerställa att de uppfyller vissa kriterier.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Definierar en immutabel Record-klass, UserDTO (Data Transfer Object).
// En record används för att skapa klasser som huvudsakligen hanterar data (som DTOs) med minimal boilerplate-kod.
public record UserDTO(

        // Validerar att username inte är tomt (varken null eller endast whitespace).
        @NotBlank(message = "Field cannot be empty")
        // Validerar att username är mellan 3 och 12 tecken långt.
        @Size(message = "Username must be between 3 and 12 characters long", min = 3, max = 12)
        String username,

        // Validerar att password inte är tomt (varken null eller endast whitespace).
        @NotBlank(message = "Field cannot be empty")
        // Validerar att password är mellan 3 och 12 tecken långt.
        @Size(message = "Username must be between 3 and 12 characters long", min = 3, max = 12)
        String password,

        // UserRole används för att representera användarens roll i systemet.
        // Ingen validering anges här, men det bör aldrig vara null för att säkerställa att varje användare har en roll.
        UserRole userRole

) {

}

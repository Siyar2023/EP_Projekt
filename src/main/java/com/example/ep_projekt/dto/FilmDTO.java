package com.example.ep_projekt.dto;
// Importerar ett paket som innehåller klassen Response. Den används för att implementera interfacet Response i denna klass.
import com.example.ep_projek.response.Response;

// Definierar en klass FilmDTO (Data Transfer Object) som implementerar interfacet Response.
// DTO används ofta för att överföra data mellan olika lager i en applikation.
public class FilmDTO implements Response {

    // Fält för att lagra en unik identifierare för en film.
    private Long id;

    // Fält för att lagra titeln på en film.
    private String title;

    // Fält för att lagra användarens åsikt om filmen.
    private String opinion;

    // Fält för att lagra en beskrivning av filmen.
    private String description;

    // Getter-metod för att hämta värdet på id.
    public Long getId() {
        return id;
    }

    // Setter-metod för att sätta värdet på id.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter-metod för att hämta värdet på title.
    public String getTitle() {
        return title;
    }

    // Setter-metod för att sätta värdet på title.
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter-metod för att hämta värdet på opinion.
    public String getOpinion() {
        return opinion;
    }

    // Setter-metod för att sätta värdet på opinion.
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    // Getter-metod för att hämta värdet på description.
    public String getDescription() {
        return description;
    }

    // Setter-metod för att sätta värdet på description.
    public void setDescription(String description) {
        this.description = description;
    }
}

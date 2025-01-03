package com.example.ep_projekt.dto;

// Definierar en klass UserFilmDTO (Data Transfer Object) som används för att hantera data
// mellan olika lager i en applikation, exempelvis för att representera en användares filmdata.
public class UserFilmDTO {

    // Fält för att lagra filmens unika ID. Här används typen Integer.
    private Integer id;

    // Fält för att lagra titeln på filmen.
    private String title;

    // Fält för att lagra användarens åsikt om filmen.
    private String opinion;

    // Getter-metod för id-fältet.
    // Används för att hämta värdet på id.
    public Integer getId() {
        return id;
    }

    // Setter-metod för id-fältet.
    // Används för att sätta eller ändra värdet på id.
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter-metod för title-fältet.
    // Används för att hämta värdet på titel.
    public String getTitle() {
        return title;
    }

    // Setter-metod för title-fältet.
    // Används för att sätta eller ändra värdet på titel.
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter-metod för opinion-fältet.
    // Används för att hämta värdet på användarens åsikt om filmen.
    public String getOpinion() {
        return opinion;
    }

    // Setter-metod för opinion-fältet.
    // Används för att sätta eller ändra värdet på användarens åsikt.
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}

package com.example.ep_projekt.model;

// Importerar nödvändiga JPA-annoteringar för att använda ORM (Object-Relational Mapping)
import jakarta.persistence.*;

// Denna klass representerar en genre och den är embeddable, vilket innebär att den kan inkluderas som en del av en annan entitet.
@Embeddable
public class Genre {

    // Fältet för att lagra genre-ID
    private Long id;

    // Fältet för att lagra genre-namn
    private String name;

    // Getter-metod för genre-ID
    public Long getId() {
        return id;
    }

    // Setter-metod för genre-ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter-metod för genre-namn
    public String getName() {
        return name;
    }

    // Setter-metod för genre-namn
    public void setName(String name) {
        this.name = name;
    }
}

package com.example.ep_projekt.repository;

import com.example.ep_projekt.model.CustomUser;
import com.example.ep_projekt.model.FilmModel;
import com.example.ep_projekt.model.UserFilm;
import org.apache.catalina.User; // Den här importen används inte och kan tas bort om inte nödvändig.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {

    /**
     * Hittar en UserFilm-post baserat på en specifik film och användare.
     * @param film FilmModel-objekt som representerar filmen.
     * @param user CustomUser-objekt som representerar användaren.
     * @return En Optional som innehåller UserFilm om den hittas, annars tom.
     */
    Optional<UserFilm> findByFilmModelAndCustomUser(FilmModel film, CustomUser user);
}

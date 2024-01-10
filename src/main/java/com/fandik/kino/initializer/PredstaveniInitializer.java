package com.fandik.kino.initializer;

import com.fandik.kino.entity.FilmEntity;
import com.fandik.kino.entity.PredstaveniEntity;
import com.fandik.kino.repository.PredstaveniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@DependsOn("filmInitializer")
public class PredstaveniInitializer implements CommandLineRunner {

    @Autowired
    private PredstaveniRepository predstaveniRepository;

    @Override
    public void run(String... args) throws Exception {
        FilmEntity fandaVelikyFilm = new FilmEntity();
        FilmEntity narutoFilm = new FilmEntity();
        FilmEntity deathNoteFilm = new FilmEntity();

        fandaVelikyFilm.setId(1L);

        narutoFilm.setId(2L);

        deathNoteFilm.setId(3L);

        PredstaveniEntity fandaVeliky = new PredstaveniEntity();
        PredstaveniEntity naruto = new PredstaveniEntity();
        PredstaveniEntity naruto2 = new PredstaveniEntity();
        PredstaveniEntity deathNote = new PredstaveniEntity();

        fandaVeliky.setId(1L);
        fandaVeliky.setNazev("Velkolepá podívaná na Fandu velikého");
        fandaVeliky.setDatum(Date.from(Instant.now().plus(Duration.ofDays(1))));
        fandaVeliky.setFilm(fandaVelikyFilm);

        naruto.setId(2L);
        naruto.setNazev("Příběh o ninjovi který vás vezme za srdce");
        naruto.setDatum(Date.from(Instant.now().plus(Duration.ofDays(3)).plus(Duration.ofHours(2))));
        naruto.setFilm(narutoFilm);

        naruto2.setId(3L);
        naruto2.setNazev("Pro velký zájem budeme dávat znovu");
        naruto2.setDatum(Date.from(Instant.now().plus(Duration.ofDays(7)).plus(Duration.ofMinutes(24))));
        naruto2.setFilm(narutoFilm);

        deathNote.setId(4L);
        deathNote.setNazev("Zajímavé anime");
        deathNote.setDatum(Date.from(Instant.now().plus(Duration.ofDays(10)).plus(Duration.ofHours(5)).plus(Duration.ofMinutes(5))));
        deathNote.setFilm(deathNoteFilm);

        predstaveniRepository.saveAll(List.of(fandaVeliky, naruto, naruto2, deathNote));

    }
}

package com.fandik.kino.initializer;

import com.fandik.kino.entity.FilmEntity;
import com.fandik.kino.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmInitializer implements CommandLineRunner {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void run(String... args) throws Exception {
        FilmEntity fandaVeliky = new FilmEntity();
        FilmEntity naruto = new FilmEntity();
        FilmEntity deathNote = new FilmEntity();

        fandaVeliky.setId(1L);
        fandaVeliky.setNazev("Fanda veliký");
        fandaVeliky.setPopis("Příběh o neohroženém fandovi který se nebojí vůbec ničeho.");

        naruto.setId(2L);
        naruto.setNazev("Naruto");
        naruto.setPopis("Naruto, chlapec s nezvyklými schopnostmi a devítiocasou liškou, se stal hrdinou populárního seriálu s 11 řadami, 3 filmy a NARUTO: SHIPPÛDEN.");

        deathNote.setId(3L);
        deathNote.setNazev("Death note");
        deathNote.setPopis("Japonský středoškolák dostane do rukou mystický zápisník, který zabije každého, jehož jméno do něj napíše.");

        filmRepository.saveAll(List.of(fandaVeliky, naruto, deathNote));
    }
}

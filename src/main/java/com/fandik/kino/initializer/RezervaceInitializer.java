package com.fandik.kino.initializer;

import com.fandik.kino.entity.PredstaveniEntity;
import com.fandik.kino.entity.RezervaceEntity;
import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.repository.RezervaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DependsOn("predstaveniInitializer")
public class RezervaceInitializer implements CommandLineRunner {

    @Autowired
    private RezervaceRepository rezervaceRepository;

    private UzivatelInitializer uzivatelInitializer;

    private PredstaveniInitializer predstaveniInitializer;

    @Override
    public void run(String... args) throws Exception {

        UzivatelEntity fanda = new UzivatelEntity();
        UzivatelEntity lojza = new UzivatelEntity();

        fanda.setId(2L);
        lojza.setId(3L);

        PredstaveniEntity fandaVeliky = new PredstaveniEntity();
        PredstaveniEntity naruto = new PredstaveniEntity();
        PredstaveniEntity naruto2 = new PredstaveniEntity();
        PredstaveniEntity deathNote = new PredstaveniEntity();

        fandaVeliky.setId(1L);
        naruto.setId(2L);
        naruto2.setId(3L);
        deathNote.setId(4L);

        RezervaceEntity fandaFandu = new RezervaceEntity();
        RezervaceEntity fandaNaruta = new RezervaceEntity();
        RezervaceEntity lojzaNaruta2 = new RezervaceEntity();
        RezervaceEntity fandaDeathNote = new RezervaceEntity();
        RezervaceEntity lojzaDeathNote = new RezervaceEntity();

        fandaFandu.setId(1L);
        fandaFandu.setMisto(5);
        fandaFandu.setPredstaveni(fandaVeliky);
        fandaFandu.setUzivatel(fanda);

        fandaNaruta.setId(2L);
        fandaNaruta.setMisto(12);
        fandaNaruta.setPredstaveni(naruto);
        fandaNaruta.setUzivatel(fanda);

        lojzaNaruta2.setId(3L);
        lojzaNaruta2.setMisto(1);
        lojzaNaruta2.setPredstaveni(naruto2);
        lojzaNaruta2.setUzivatel(lojza);

        fandaDeathNote.setId(4L);
        fandaDeathNote.setMisto(10);
        fandaDeathNote.setPredstaveni(deathNote);
        fandaDeathNote.setUzivatel(fanda);

        lojzaDeathNote.setId(5L);
        lojzaDeathNote.setMisto(9);
        lojzaDeathNote.setPredstaveni(deathNote);
        lojzaDeathNote.setUzivatel(lojza);

        rezervaceRepository.saveAll(List.of(fandaFandu, fandaNaruta, lojzaNaruta2, fandaDeathNote, lojzaDeathNote));

    }
}

package com.fandik.kino.initializer;

import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.repository.UzivatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UzivatelInitializer implements CommandLineRunner {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Override
    public void run(String... args) throws Exception {
        UzivatelEntity admin = new UzivatelEntity();
        UzivatelEntity fanda = new UzivatelEntity();
        UzivatelEntity lojza = new UzivatelEntity();

        admin.setId(1L);
        admin.setJmeno("Administrátor Adminovič");
        admin.setLogin("admin");
        admin.setHeslo("admin");

        fanda.setId(2L);
        fanda.setJmeno("Fandík Křivanců");
        fanda.setLogin("fanda");
        fanda.setHeslo("heslo");

        lojza.setId(3L);
        lojza.setJmeno("Lojzík Malej");
        lojza.setLogin("lojza");
        lojza.setHeslo("heslo");

        uzivatelRepository.saveAll(List.of(admin, fanda, lojza));
    }
}

package com.fandik.kino.initializer;

import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.repository.UzivatelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UzivatelInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

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
        admin.setHeslo(passwordEncoder.encode("admin"));
        admin.setRole(UzivatelEntity.Role.ROLE_ADMIN);

        fanda.setId(2L);
        fanda.setJmeno("Fandík Křivanců");
        fanda.setLogin("fanda");
        fanda.setHeslo(passwordEncoder.encode("heslo"));
        fanda.setRole(UzivatelEntity.Role.ROLE_UZIVATEL);

        lojza.setId(3L);
        lojza.setJmeno("Lojzík Malej");
        lojza.setLogin("lojza");
        lojza.setHeslo(passwordEncoder.encode("heslo"));
        lojza.setRole(UzivatelEntity.Role.ROLE_UZIVATEL);

        uzivatelRepository.saveAll(List.of(admin, fanda, lojza));
    }
}

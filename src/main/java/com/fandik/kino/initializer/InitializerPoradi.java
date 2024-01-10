package com.fandik.kino.initializer;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializerPoradi implements SmartInitializingSingleton {

    @Autowired
    private UzivatelInitializer uzivatelInitializer;
    @Autowired
    private FilmInitializer filmInitializer;
    @Autowired
    private PredstaveniInitializer predstaveniInitializer;
    @Autowired
    private RezervaceInitializer rezervaceInitializer;

    @Override
    public void afterSingletonsInstantiated() {
        try {
            uzivatelInitializer.run();
            filmInitializer.run();
            predstaveniInitializer.run();
            rezervaceInitializer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

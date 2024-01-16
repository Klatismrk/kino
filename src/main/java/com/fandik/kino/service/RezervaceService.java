package com.fandik.kino.service;

import com.fandik.kino.entity.RezervaceEntity;
import com.fandik.kino.repository.RezervaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RezervaceService {

    private final RezervaceRepository rezervaceRepository;

    public RezervaceService(RezervaceRepository rezervaceRepository) {
        this.rezervaceRepository = rezervaceRepository;
    }

    public List<RezervaceEntity> findAll() {
        return StreamSupport.stream(rezervaceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<RezervaceEntity> findById(Long id) {
        return rezervaceRepository.findById(id);
    }

    public RezervaceEntity save(RezervaceEntity rezervaceEntity) {
        return rezervaceRepository.save(rezervaceEntity);
    }

    public void deleteById(Long id) {
        rezervaceRepository.deleteById(id);
    }

    public List<RezervaceEntity> findByUzivatelId(Long id) {
        return rezervaceRepository.findByUzivatelId(id);
    }

    public Optional<RezervaceEntity> findByUzivatelIdAndId(Long uzivatelId, Long id) {
        return rezervaceRepository.findByUzivatelIdAndId(uzivatelId, id);
    }
}

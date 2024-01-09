package com.fandik.kino.service;

import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.repository.UzivatelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UzivatelService {

    private final UzivatelRepository uzivatelRepository;

    public UzivatelService(UzivatelRepository uzivatelRepository) {
        this.uzivatelRepository = uzivatelRepository;
    }

    public List<UzivatelEntity> findAll() {
        return StreamSupport.stream(uzivatelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<UzivatelEntity> findById(Long id) {
        return uzivatelRepository.findById(id);
    }

    public UzivatelEntity save(UzivatelEntity uzivatelEntity) {
        return uzivatelRepository.save(uzivatelEntity);
    }

    public void deleteById(Long id) {
        uzivatelRepository.deleteById(id);
    }
}

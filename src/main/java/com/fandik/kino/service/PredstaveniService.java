package com.fandik.kino.service;

import com.fandik.kino.entity.PredstaveniEntity;
import com.fandik.kino.repository.PredstaveniRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PredstaveniService {

    private final PredstaveniRepository predstaveniRepository;

    public PredstaveniService(PredstaveniRepository predstaveniRepository) {
        this.predstaveniRepository = predstaveniRepository;
    }

    public List<PredstaveniEntity> findAll() {
            return StreamSupport.stream(predstaveniRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<PredstaveniEntity> findById(Long id) {
        return predstaveniRepository.findById(id);
    }

    public PredstaveniEntity save(PredstaveniEntity predstaveniEntity) {
        return predstaveniRepository.save(predstaveniEntity);
    }

    public void deleteById(Long id) {
        predstaveniRepository.deleteById(id);
    }
}

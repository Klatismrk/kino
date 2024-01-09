package com.fandik.kino.service;

import com.fandik.kino.entity.FilmEntity;
import com.fandik.kino.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmEntity> findAll() {
        return StreamSupport.stream(filmRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<FilmEntity> findById(Long id) {
        return filmRepository.findById(id);
    }

    public FilmEntity save(FilmEntity filmEntity) {
        return filmRepository.save(filmEntity);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
}

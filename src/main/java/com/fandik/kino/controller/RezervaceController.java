package com.fandik.kino.controller;

import com.fandik.kino.entity.RezervaceEntity;
import com.fandik.kino.service.RezervaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rezervace")
@CrossOrigin(origins = "http://localhost:3000")
public class RezervaceController {

    private final RezervaceService rezervaceService;

    public RezervaceController(RezervaceService rezervaceService) {
        this.rezervaceService = rezervaceService;
    }

    @GetMapping
    public ResponseEntity<List<RezervaceEntity>> getAllRezervace() {
        return new ResponseEntity<>(rezervaceService.findAll().stream().collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RezervaceEntity> getOneById(@PathVariable("id") Long id) {
        Optional<RezervaceEntity> findRezervace = rezervaceService.findById(id);
        return findRezervace.map(rezervace -> new ResponseEntity<>(rezervace, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RezervaceEntity> save(@RequestBody RezervaceEntity rezervaceEntity) {
        return new ResponseEntity<>(rezervaceService.save(rezervaceEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        rezervaceService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

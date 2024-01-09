package com.fandik.kino.controller;

import com.fandik.kino.entity.PredstaveniEntity;
import com.fandik.kino.service.PredstaveniService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/predstaveni")
@CrossOrigin(origins = "http://localhost:3000")
public class PredstaveniController {

    private final PredstaveniService predstaveniService;

    public PredstaveniController(PredstaveniService predstaveniService) {
        this.predstaveniService = predstaveniService;
    }

    @GetMapping
    public ResponseEntity<List<PredstaveniEntity>> getAllPredstaveni() {
        return new ResponseEntity<>(predstaveniService.findAll().stream().collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredstaveniEntity> getOneById(@PathVariable("id") Long id) {
        Optional<PredstaveniEntity> findPredstaveni = predstaveniService.findById(id);
        return findPredstaveni.map(predstaveni -> new ResponseEntity<>(predstaveni, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PredstaveniEntity> save(@RequestBody PredstaveniEntity predstaveniEntity) {
        return new ResponseEntity<>(predstaveniService.save(predstaveniEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        predstaveniService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

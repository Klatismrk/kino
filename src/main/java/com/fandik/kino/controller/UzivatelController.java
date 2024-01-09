package com.fandik.kino.controller;

import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.service.UzivatelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/uzivatel")
@CrossOrigin(origins = "http://localhost:3000")
public class UzivatelController {

    private final UzivatelService uzivatelService;

    public UzivatelController(UzivatelService uzivatelService) {
        this.uzivatelService = uzivatelService;
    }

    @GetMapping
    public ResponseEntity<List<UzivatelEntity>> getAllUzivatel() {
        return new ResponseEntity<>(uzivatelService.findAll().stream().collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UzivatelEntity> getById(@PathVariable("id") Long id) {
        Optional<UzivatelEntity> findUzivatel = uzivatelService.findById(id);
        return findUzivatel.map(uzivatel -> new ResponseEntity<>(uzivatel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UzivatelEntity> save(@RequestBody UzivatelEntity uzivatelEntity) {
        return new ResponseEntity<>(uzivatelService.save(uzivatelEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUzivatel(@PathVariable("id") Long id) {
        uzivatelService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.fandik.kino.controller;

import com.fandik.kino.entity.RezervaceEntity;
import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.service.RezervaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<RezervaceEntity>> getAllRezervace(@AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                return new ResponseEntity<>(rezervaceService.findAll().stream().collect(Collectors.toList()), HttpStatus.OK);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                Long uzivatelId = uzivatelEntity.getId();
                return new ResponseEntity<>(rezervaceService.findByUzivatelId(uzivatelId), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RezervaceEntity> getOneById(@PathVariable("id") Long id, @AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                Optional<RezervaceEntity> findRezervace = rezervaceService.findById(id);
                return findRezervace.map(rezervace -> new ResponseEntity<>(rezervace, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else if (roles.contains("ROLE_UZIVATEL")) {
                Long uzivatelId = uzivatelEntity.getId();
                Optional<RezervaceEntity> findRezervaceByIdAndUzivatelId = rezervaceService.findByUzivatelIdAndId(uzivatelId, id);
                return findRezervaceByIdAndUzivatelId.map(rezervace -> new ResponseEntity<>(rezervace, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<RezervaceEntity> save(@RequestBody RezervaceEntity rezervaceEntity, @AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                return new ResponseEntity<>(rezervaceService.save(rezervaceEntity), HttpStatus.CREATED);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id, @AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                rezervaceService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                Optional<RezervaceEntity> findRezervaceByUzivatelIdAndId = rezervaceService.findByUzivatelIdAndId(uzivatelEntity.getId(), id);
                if (findRezervaceByUzivatelIdAndId.isPresent()) {
                    rezervaceService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

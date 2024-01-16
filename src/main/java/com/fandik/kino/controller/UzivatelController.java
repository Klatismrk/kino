package com.fandik.kino.controller;

import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.service.UzivatelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/uzivatel")
@CrossOrigin(origins = "http://localhost:3000")
public class UzivatelController {

    private final UzivatelService uzivatelService;
    private final PasswordEncoder passwordEncoder;

    public UzivatelController(UzivatelService uzivatelService, PasswordEncoder passwordEncoder) {
        this.uzivatelService = uzivatelService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<UzivatelEntity>> getAllUzivatel(@AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                return new ResponseEntity<>(uzivatelService.findAll().stream().collect(Collectors.toList()), HttpStatus.OK);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UzivatelEntity> getById(@PathVariable("id") Long id, @AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            Optional<UzivatelEntity> findUzivatel = uzivatelService.findById(id);
            if (roles.contains("ROLE_ADMIN")) {
                return findUzivatel.map(uzivatel -> new ResponseEntity<>(uzivatel, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else if (roles.contains("ROLE_UZIVATEL")) {
                if (uzivatelEntity.getId().equals(id)) {
                    return findUzivatel.map(uzivatel -> new ResponseEntity<>(uzivatel, HttpStatus.OK))
                            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<UzivatelEntity> save(@RequestBody UzivatelEntity uzivatelEntityCreated, @AuthenticationPrincipal UzivatelEntity uzivatelEntityLoggedIn) {
        if (uzivatelEntityLoggedIn!=null) {
            List<String> roles = uzivatelEntityLoggedIn.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            uzivatelEntityCreated.setHeslo(passwordEncoder.encode(uzivatelEntityCreated.getPassword()));
            if (roles.contains("ROLE_ADMIN")) {
                return new ResponseEntity<>(uzivatelService.save(uzivatelEntityCreated), HttpStatus.CREATED);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUzivatel(@PathVariable("id") Long id, @AuthenticationPrincipal UzivatelEntity uzivatelEntity) {
        if (uzivatelEntity!=null) {
            List<String> roles = uzivatelEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            if (roles.contains("ROLE_ADMIN")) {
                uzivatelService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else if (roles.contains("ROLE_UZIVATEL")) {
                if (uzivatelEntity.getId().equals(id)) {
                    uzivatelService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}

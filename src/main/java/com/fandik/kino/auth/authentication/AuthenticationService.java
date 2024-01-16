package com.fandik.kino.auth.authentication;

import com.fandik.kino.auth.config.JwtService;
import com.fandik.kino.entity.UzivatelEntity;
import com.fandik.kino.repository.UzivatelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UzivatelRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        var user = UzivatelEntity.builder()
                .jmeno(request.getJmeno())
                .login(request.getLogin())
                .heslo(passwordEncoder.encode(request.getHeslo()))
                .role(UzivatelEntity.Role.ROLE_UZIVATEL)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getHeslo()));
        var user = repository.findByLogin(request.getLogin()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
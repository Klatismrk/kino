/**
 *
 * PRAVDĚPODOBNĚ NEPOTŘEBUJI KDYŽ MÁM ZABEZPEČENÍ V KONTROLORECH
 *
 * */
package com.fandik.kino.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/**")
//                .permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/film/**", "/api/predstaveni/**")
//                .permitAll()
//                .requestMatchers("/api/uzivatel/**", "/api/rezervace/**")
//                .authenticated()
//                .requestMatchers("/api/uzivatel/**", "/api/film/**", "/api/predstaveni/**")
//                .hasAnyRole("ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

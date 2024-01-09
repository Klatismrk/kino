package com.fandik.kino.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "uzivatel")
@Table(name = "UZIVATELE")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UzivatelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String jmeno;

    @NotNull
    private String heslo;

}

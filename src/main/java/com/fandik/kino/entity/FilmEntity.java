package com.fandik.kino.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "film")
@Table(name = "FILMY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nazev;

    private String popis;

}

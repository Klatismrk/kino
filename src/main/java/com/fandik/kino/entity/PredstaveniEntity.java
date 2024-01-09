package com.fandik.kino.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "predstaveni")
@Table(name = "PREDSTAVENI")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PredstaveniEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private FilmEntity film;

    @NotNull
    private String nazev;

    @NotNull
    private Date datum;

}

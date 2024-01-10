package com.fandik.kino.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "rezervace")
@Table(name = "REZERVACE")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RezervaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private UzivatelEntity uzivatel;

    @NotNull
    @ManyToOne
    private PredstaveniEntity predstaveni;

    @NotNull
    private int misto;

}

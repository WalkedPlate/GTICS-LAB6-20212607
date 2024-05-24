package com.example.gticslab620212607.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mesas")
public class Mesas {

    @Id
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(max=100, message = "el nombre debe tener como máximo 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @Positive
    @Digits(integer = 10, fraction = 0)
    @Column(name = "capacidad")
    private String capacidad;

    @Positive
    @Digits(integer = 10, fraction = 0)
    @Column(name = "disponible")
    private Integer disponible;

    @NotBlank
    @Size(max=100, message = "debe tener como máximo 100 caracteres")
    @Column(name = "ubicacion")
    private String ubicacion;

}

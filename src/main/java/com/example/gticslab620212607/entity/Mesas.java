package com.example.gticslab620212607.entity;

import jakarta.persistence.*;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "capacidad")
    private String capacidad;

    @Column(name = "disponible")
    private Integer disponible;

    @Column(name = "ubicacion")
    private String ubicacion;

}

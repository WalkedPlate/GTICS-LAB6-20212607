package com.example.gticslab620212607.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reservas")
public class Reservas {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_in")
    private String fechaIn;

    @Column(name = "fecha_fin")
    private String fechaFin;

    @Column(name = "cantidad")
    private Integer cantidad;


    @ManyToOne
    @JoinColumn(name = "mesas_id")
    private Mesas mesas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

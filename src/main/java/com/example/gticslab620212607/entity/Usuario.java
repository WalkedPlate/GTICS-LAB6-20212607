package com.example.gticslab620212607.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}

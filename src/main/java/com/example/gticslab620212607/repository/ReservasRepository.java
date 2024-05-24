package com.example.gticslab620212607.repository;

import com.example.gticslab620212607.entity.Reservas;
import com.example.gticslab620212607.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas,Integer> {

    List<Reservas> findByUsuario(Usuario usuario);

}

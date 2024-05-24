package com.example.gticslab620212607.repository;

import com.example.gticslab620212607.entity.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas,Integer> {
}

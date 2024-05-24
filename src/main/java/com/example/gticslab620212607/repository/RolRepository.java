package com.example.gticslab620212607.repository;

import com.example.gticslab620212607.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer> {
}

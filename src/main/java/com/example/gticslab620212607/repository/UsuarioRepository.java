package com.example.gticslab620212607.repository;

import com.example.gticslab620212607.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    Usuario findByCorreo(String correo);
}

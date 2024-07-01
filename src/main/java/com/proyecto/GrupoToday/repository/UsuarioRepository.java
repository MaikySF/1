package com.proyecto.GrupoToday.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.GrupoToday.models.UsuarioAdmin;


public interface UsuarioRepository extends JpaRepository<UsuarioAdmin, Integer>{

    UsuarioAdmin findByEmail(String email);
    
}

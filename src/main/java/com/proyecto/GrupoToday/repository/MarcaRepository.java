package com.proyecto.GrupoToday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.GrupoToday.models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{

}

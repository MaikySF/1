package com.proyecto.GrupoToday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.GrupoToday.models.Modelo;
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{

}

package com.proyecto.GrupoToday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.GrupoToday.models.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}

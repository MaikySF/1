package com.proyecto.GrupoToday.repository;

import com.proyecto.GrupoToday.models.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    List<Boleta> findByClienteId(Long clienteId);
}

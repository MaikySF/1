package com.proyecto.GrupoToday.repository;

import com.proyecto.GrupoToday.models.DetalleBoleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {

    List<DetalleBoleta> findByBoletaClienteId(Long clienteId);

    DetalleBoleta findByBoletaIdAndZapatillaId(Long boletaId, Long zapatillaId);

    void deleteByBoletaId(Long boletaId);
}

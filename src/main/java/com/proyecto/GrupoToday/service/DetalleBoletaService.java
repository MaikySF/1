package com.proyecto.GrupoToday.service;

import com.proyecto.GrupoToday.models.DetalleBoletaDTO;

import java.util.List;

public interface DetalleBoletaService {
    List<DetalleBoletaDTO> getDetallePorClienteId(Long clienteId);
}

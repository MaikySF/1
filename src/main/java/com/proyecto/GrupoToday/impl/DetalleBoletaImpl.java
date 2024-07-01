package com.proyecto.GrupoToday.impl;


import com.proyecto.GrupoToday.models.Boleta;
import com.proyecto.GrupoToday.models.DetalleBoleta;
import com.proyecto.GrupoToday.models.DetalleBoletaDTO;
import com.proyecto.GrupoToday.repository.BoletaRepository;
import com.proyecto.GrupoToday.repository.DetalleBoletaRepository;
import com.proyecto.GrupoToday.service.DetalleBoletaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DetalleBoletaImpl implements DetalleBoletaService {


    private DetalleBoletaRepository detalleBoletaRepository;

    private BoletaRepository boletaRepository;


    @Override
    public List<DetalleBoletaDTO> getDetallePorClienteId(Long clienteId) {
        List<Boleta> boletas = boletaRepository.findByClienteId(clienteId);
        return boletas.stream()
                .flatMap(boleta -> detalleBoletaRepository.findByBoletaClienteId(boleta.getId()).stream())
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    private DetalleBoletaDTO convertirADTO(DetalleBoleta detalleBoleta) {
        DetalleBoletaDTO dto = new DetalleBoletaDTO();
        dto.setId(detalleBoleta.getId());
        dto.setBoletaId(detalleBoleta.getBoleta().getId());
        dto.setZapatillaId(detalleBoleta.getZapatilla().getId());
        dto.setCantidad(detalleBoleta.getCantidad());
        dto.setPreTotal(detalleBoleta.getPre_total());
        return dto;
    }



}

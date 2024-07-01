package com.proyecto.GrupoToday.service;


import com.proyecto.GrupoToday.models.Boleta;
import com.proyecto.GrupoToday.models.DetalleBoleta;

import java.util.List;

public interface CarritoService {

    List<DetalleBoleta> agregarZapatilla(Long clienteId, Long zapatillaId, int cantidad);
    List<DetalleBoleta> eliminarZapatilla(Long clienteId, Long zapatillaId);
    List<DetalleBoleta> obtenerCarrito(Long clienteId);

    List<DetalleBoleta> modificarCantidad(Long clienteId, Long zapatillaId, int cantidad);
    Boleta finalizarCompra(Long clienteId);

}

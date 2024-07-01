package com.proyecto.GrupoToday.impl;


import com.proyecto.GrupoToday.models.Boleta;
import com.proyecto.GrupoToday.models.Cliente;
import com.proyecto.GrupoToday.models.DetalleBoleta;
import com.proyecto.GrupoToday.models.Zapatilla1;
import com.proyecto.GrupoToday.repository.BoletaRepository;
import com.proyecto.GrupoToday.repository.ClienteRepository;
import com.proyecto.GrupoToday.repository.DetalleBoletaRepository;
import com.proyecto.GrupoToday.repository.Zapatilla1Repository;
import com.proyecto.GrupoToday.service.CarritoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private BoletaRepository boletaRepository;

    private DetalleBoletaRepository detalleBoletaRepository;

    private Zapatilla1Repository zapatillaRepository;

    private ClienteRepository clienteRepository;

    @Override
    public List<DetalleBoleta> agregarZapatilla(Long clienteId,
                                                Long zapatillaId,
                                                int cantidad) {

        // Buscar boleta activa (temporal) para el cliente
        List<Boleta> boletas = boletaRepository.findByClienteId(clienteId);
        Boleta boleta;

        if (boletas.isEmpty()) {
            // Crear una nueva boleta si no existe
            boleta = new Boleta();
            Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            boleta.setCliente(cliente);
            boleta.setFech_bol(LocalDate.now());
            boletaRepository.save(boleta);
        } else {
            // Usar la boleta existente
            boleta = boletas.get(0);
        }

        // Buscar la zapatilla por ID
        Zapatilla1 zapatilla = zapatillaRepository.findById(zapatillaId).orElseThrow(() -> new RuntimeException("Zapatilla no encontrada"));

        // Buscar el detalle de la boleta para la zapatilla específica
        DetalleBoleta detalleBoleta = detalleBoletaRepository.findByBoletaIdAndZapatillaId(boleta.getId(), zapatillaId);

        if (detalleBoleta == null) {
            // Crear un nuevo detalle de boleta si no existe
            detalleBoleta = new DetalleBoleta();
            detalleBoleta.setBoleta(boleta);
            detalleBoleta.setZapatilla(zapatilla);
            detalleBoleta.setCantidad(cantidad);
            detalleBoleta.setPre_total(zapatilla.getPre_zapa() * cantidad);
        } else {
            // Actualizar la cantidad y el precio total si el detalle ya existe
            detalleBoleta.setCantidad(detalleBoleta.getCantidad() + cantidad);
            detalleBoleta.setPre_total(zapatilla.getPre_zapa() * detalleBoleta.getCantidad());
        }

        // Guardar el detalle de boleta
        detalleBoletaRepository.save(detalleBoleta);

        // Retornar todos los detalles del carrito para el cliente
        return detalleBoletaRepository.findByBoletaClienteId(clienteId);
    }

    @Override
    public List<DetalleBoleta> eliminarZapatilla(Long clienteId,
                                                 Long zapatillaId) {
        List<Boleta> boletas = boletaRepository.findByClienteId(clienteId);
        for (Boleta boleta : boletas) {
            DetalleBoleta detaBoleta = detalleBoletaRepository.findByBoletaIdAndZapatillaId(boleta.getId(),
                    zapatillaId);
            if (detaBoleta != null) {
                detalleBoletaRepository.delete(detaBoleta);
            }
        }
        return detalleBoletaRepository.findByBoletaClienteId(clienteId);
    }

    @Override
    public List<DetalleBoleta> obtenerCarrito(Long clienteId) {
        return detalleBoletaRepository.findByBoletaClienteId(clienteId);
    }

    @Override
    public List<DetalleBoleta> modificarCantidad(Long clienteId, Long zapatillaId, int cantidad) {

        List<Boleta> boletas = boletaRepository.findByClienteId(clienteId);
        if (boletas.isEmpty()) {
            throw new RuntimeException("No se encontró ninguna boleta activa para el cliente.");
        }

        Boleta boleta = boletas.get(0);
        DetalleBoleta detalleBoleta = detalleBoletaRepository.findByBoletaIdAndZapatillaId(boleta.getId(), zapatillaId);

        if (detalleBoleta == null) {
            throw new RuntimeException("No se encontró la zapatilla en el carrito.");
        }

        detalleBoleta.setCantidad(cantidad);
        detalleBoleta.setPre_total(detalleBoleta.getZapatilla().getPre_zapa() * cantidad);
        detalleBoletaRepository.save(detalleBoleta);

        return detalleBoletaRepository.findByBoletaClienteId(clienteId);
    }

    @Override
    public Boleta finalizarCompra(Long clienteId) {

        List<Boleta> boletas = boletaRepository.findByClienteId(clienteId);
        if (boletas.isEmpty()) {
            throw new RuntimeException("No se encontró ninguna boleta activa para el cliente.");
        }

        Boleta boleta = boletas.get(0);
        List<DetalleBoleta> detalles = detalleBoletaRepository.findByBoletaClienteId(clienteId);

        if (detalles.isEmpty()) {
            throw new RuntimeException("El carrito está vacío.");
        }

        // Guardar la boleta
        boleta.setFech_bol(LocalDate.now());
        boleta.setDetalles(detalles);
        boletaRepository.save(boleta);

        // Asignar la boleta a cada detalle y guardar los detalles
        for (DetalleBoleta detalle : detalles) {
            detalle.setBoleta(boleta);
            detalleBoletaRepository.save(detalle);
        }

        // Eliminar los detalles del carrito
        detalleBoletaRepository.deleteAll(detalles);

        return boleta;
    }


}

package com.proyecto.GrupoToday.controller;


import com.proyecto.GrupoToday.models.DetalleBoleta;
import com.proyecto.GrupoToday.models.DetalleBoletaDTO;
import com.proyecto.GrupoToday.service.CarritoService;
import com.proyecto.GrupoToday.service.DetalleBoletaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CarritoController {

    private CarritoService carritoService;
    private DetalleBoletaService detalleBoletaService;



    @GetMapping("/{clienteId}")
    public ResponseEntity<List<DetalleBoletaDTO>> obtenerCarrito(@PathVariable Long clienteId) {
        List<DetalleBoletaDTO> carrito = detalleBoletaService.getDetallePorClienteId(clienteId);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarZapatilla(
            @RequestParam Long clienteId,
            @RequestParam Long zapatillaId,
            @RequestParam int cantidad) {

        carritoService.agregarZapatilla(clienteId, zapatillaId, cantidad);
        return ResponseEntity.ok("Zapatilla agregada al carrito");
    }

    //MODIFICAR
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarCantidad(@RequestParam Long clienteId,
                                                                 @RequestParam Long zapatillaId,
                                                                 @RequestParam int cantidad) {
        carritoService.modificarCantidad(clienteId, zapatillaId, cantidad);
        return ResponseEntity.ok("Cantidad de zapatilla modificada en el carrito");
    }

    //ELIMINACION DE ITEM
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarZapatilla(@RequestParam Long clienteId,
                                                                 @RequestParam Long zapatillaId) {
        List<DetalleBoleta> carrito = carritoService.eliminarZapatilla(clienteId, zapatillaId);

        String mensaje = "Zapatilla eliminada con éxito.";
        return ResponseEntity.ok(mensaje);
    }

    //FINALIZAR CARRITO COMPRA
    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarCompra(@RequestParam Long clienteId) {
        carritoService.finalizarCompra(clienteId);
        return ResponseEntity.ok("Compra finalizada");
    }

}

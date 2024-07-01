package com.proyecto.GrupoToday.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.GrupoToday.models.Marca;
import com.proyecto.GrupoToday.service.MarcaService;
@RestController
@RequestMapping("/api/marca")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/listar-todo")
	public List<Marca> listaMarcas(){
		return marcaService.listarMarca();
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Map<String, String>> guardarMarca(@RequestBody Marca marca){
		try {
			marcaService.addMarca(marca);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Marca Guardado Exitosamente");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error al guardar la Marca");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, String>> eliminarMarcas(@PathVariable Integer id){
		
		try {
			marcaService.eliminarMarca(id);
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("message", "marca eliminado");
			return ResponseEntity.ok(responseMap);
		} catch (IllegalArgumentException e) {
			Map<String, String> erroMap = new HashMap<>();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroMap);
		}
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, String>> actuaizarMarcas(@PathVariable Integer id, @RequestBody Marca marca) {
	    try {
	        Marca marcaActualizada = marcaService.actualizarMarca(id, marca);
	        if (marcaActualizada != null) {
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Marca actualizada exitosamente");
	            return ResponseEntity.ok(response);
	        } else {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "No se encontró la marca");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    } catch (Exception e) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Error al actualizar la marca");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}
}

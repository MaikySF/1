package com.proyecto.GrupoToday.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.GrupoToday.models.Modelo;
import com.proyecto.GrupoToday.service.ModeloService;

@RestController
@RequestMapping("/api/modelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloController {
	@Autowired
	private ModeloService modeloService;

	@GetMapping("/listar-todo")
	public List<Modelo> listaModelos() {
		return modeloService.listarModelo();
	}

	@PostMapping("/guardar")
	public ResponseEntity<Map<String, String>> guardarModelo(@RequestBody Modelo modelo) {

		try {
			modeloService.addModelo(modelo);
			Map<String, String> response = new HashMap<>();
			response.put("message", "MOdelo Guardado Exitosamente");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error al Guardaar");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, String>> eliminarModelo(@PathVariable Integer id) {
		try {
			modeloService.eliminarModelo(id);
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("message", "Modelo Eliminado Correctamente");
			return ResponseEntity.ok(responseMap);
		} catch (IllegalArgumentException e) {
			Map<String, String> erroMap = new HashMap<>();
			erroMap.put("message", "error al eliminar Modelo");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroMap);
		}
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, String>> actualizarModelo(@PathVariable Integer id, @RequestBody Modelo modelo) {
	    try {
	        Modelo modeloActualizado = modeloService.actualizarModelo(id, modelo);
	        if (modeloActualizado != null) {
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Modelo actualizado exitosamente");
	            return ResponseEntity.ok(response);
	        } else {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "No se encontró el modelo");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    } catch (Exception e) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Error al actualizar el modelo");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

}

package com.proyecto.GrupoToday.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.GrupoToday.models.Categoria;
import com.proyecto.GrupoToday.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/listar-todo")
	public List<Categoria> listaCategorias(){
		return categoriaService.listarCategoria();
				
	}
    @PostMapping("/guardar")
    public ResponseEntity<Map<String, String>> guardarCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaService.addCategoria(categoria);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Categoria guardada exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al guardar categoria");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Integer id){
		try {
			categoriaService.eliminarCategoria(id);
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("message", "Categoria eliminado");
			return ResponseEntity.ok(responseMap);
		} catch (IllegalArgumentException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("message", "Error al Eliminar Categoria");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
		}
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, String>> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
	    try {
	        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
	        if (categoriaActualizada != null) {
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Categoria actualizada exitosamente");
	            return ResponseEntity.ok(response);
	        } else {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "No se encontró la categoría");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    } catch (IllegalArgumentException e) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Error al actualizar la categoría");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

}

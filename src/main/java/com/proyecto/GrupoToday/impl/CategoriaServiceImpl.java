package com.proyecto.GrupoToday.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.GrupoToday.models.Categoria;
import com.proyecto.GrupoToday.repository.CategoriaRepository;
import com.proyecto.GrupoToday.service.CategoriaService;
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> listarCategoria() {
		
		return categoriaRepository.findAll();
	}

	@Override
	public void addCategoria(Categoria obj) {
		try {
			categoriaRepository.save(obj);
		} catch (Exception e) {
			System.out.println("Error al agregar Categoria: " + e.getMessage());
		}

	}

	@Override
	public void eliminarCategoria(Integer id) {
		Optional<Categoria> encontrarPorIdOptional = categoriaRepository.findById(id);
		if (encontrarPorIdOptional.isPresent()) {
		categoriaRepository.deleteById(id);
		
		} else {
			throw new IllegalArgumentException("Categoria no encontrado");
		}

	}

	@Override
	public Categoria actualizarCategoria(Integer id, Categoria obj) {
		Optional<Categoria> siEncontro = categoriaRepository.findById(id);
		if (siEncontro.isPresent()) {
			Categoria siExisteCategoria = siEncontro.get();
			siExisteCategoria.setNombre(obj.getNombre());
			return categoriaRepository.save(siExisteCategoria);
		} else {
			throw new IllegalArgumentException("Categoria no Encontrado");
		}
	}

}

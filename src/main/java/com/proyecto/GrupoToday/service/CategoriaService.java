package com.proyecto.GrupoToday.service;

import java.util.List;

import com.proyecto.GrupoToday.models.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listarCategoria();
	public void addCategoria(Categoria obj);
	void eliminarCategoria(Integer id);
	Categoria actualizarCategoria(Integer id, Categoria obj);

}

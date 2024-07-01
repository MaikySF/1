package com.proyecto.GrupoToday.service;

import java.util.List;

import com.proyecto.GrupoToday.models.Marca;

public interface MarcaService {
	public List<Marca> listarMarca();
	public void addMarca(Marca obj);
	void eliminarMarca(Integer id);
	Marca actualizarMarca(Integer id, Marca obj);

}

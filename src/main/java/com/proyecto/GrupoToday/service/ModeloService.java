package com.proyecto.GrupoToday.service;

import java.util.List;

import com.proyecto.GrupoToday.models.Modelo;

public interface ModeloService {
	public List<Modelo> listarModelo();
	public void addModelo(Modelo obj);
	void eliminarModelo(Integer id);
	Modelo actualizarModelo(Integer id, Modelo obj);
}

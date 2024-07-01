package com.proyecto.GrupoToday.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.GrupoToday.models.Modelo;
import com.proyecto.GrupoToday.repository.ModeloRepository;
import com.proyecto.GrupoToday.service.ModeloService;

@Service
public class ModeloServiceImpl implements ModeloService {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	public List<Modelo> listarModelo() {
		
		return modeloRepository.findAll();
	}

	@Override
	public void addModelo(Modelo obj) {
		try {
			modeloRepository.save(obj);
		} catch (Exception e) {
			System.out.println ("Error al Agregar Modelo: " + e.getMessage());
			
		}

	}

	@Override
	public void eliminarModelo(Integer id) {
		Optional<Modelo> siExisteOptional = modeloRepository.findById(id);
		if (siExisteOptional.isPresent())
		{
			modeloRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("Modelo no encontrado");
		}
	}

	@Override
	public Modelo actualizarModelo(Integer id, Modelo obj) {
		Optional<Modelo> siExisteOptional = modeloRepository.findById(id);
		if (siExisteOptional.isPresent()) {
			Modelo siEncontroModelo = siExisteOptional.get();
			siEncontroModelo.setNombre(obj.getNombre());
			return modeloRepository.save(siEncontroModelo);
		} else {
			throw new IllegalArgumentException("Modelo no Encontrado");
		}
		
	}

}

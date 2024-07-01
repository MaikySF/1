package com.proyecto.GrupoToday.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.GrupoToday.models.Marca;
import com.proyecto.GrupoToday.repository.MarcaRepository;
import com.proyecto.GrupoToday.service.MarcaService;
@Service
public class MarcaServiceImpl implements MarcaService {
	@Autowired
	private MarcaRepository marcaRepository;
	
	
	@Override
	public List<Marca> listarMarca() {
		// TODO Auto-generated method stub
		return marcaRepository.findAll();
	}

	@Override
	public void addMarca(Marca obj) {
		
		try {
			marcaRepository.save(obj);
		} catch (Exception e) {
			System.out.println("Error al agregar Marca: " + e.getMessage());
		}

	}

	@Override
	public void eliminarMarca(Integer id) {
		Optional<Marca> siEncontro = marcaRepository.findById(id);
		
		if (siEncontro.isPresent()) {
			marcaRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("Id No Encontrado");
		}

	}

	@Override
	public Marca actualizarMarca(Integer id, Marca obj) {
		Optional<Marca> siEncontro = marcaRepository.findById(id);
		if (siEncontro.isPresent())
		{
			Marca siExisteMarca = siEncontro.get();
			siExisteMarca.setNombre(obj.getNombre());
			return marcaRepository.save(siExisteMarca);			
		}	else {
			throw new IllegalArgumentException("Marca no encontrada");
		}
	}

}

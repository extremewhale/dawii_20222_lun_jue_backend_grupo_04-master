package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Tesis;
import com.cibertec.repository.TesisRepository;

@Service
public class TesisServicelmp implements TesisService{

	@Autowired
	private TesisRepository repository;
	
	
	@Override
	public Tesis insertaTesis(Tesis obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Tesis> listaTodos() {
		return repository.findAll();
	}
	
	@Override
	public List<Tesis> listaTesisPorTituloTemaAlumno(String titulo, String tema, int idAlumno, int estado) {
		return repository.listaTesisPorTituloTemaAlumno(titulo, tema, idAlumno, estado);
	}

	@Override
	public Tesis insertaActualizaTesis(Tesis tesis) {
		return repository.save(tesis);
	}

	@Override
	public List<Tesis> listaTesisPorTituloLike(String titulo) {
		return repository.listaPorTituloLike(titulo);
	}

	@Override
	public void eliminaTesis(int idTesis) {
		repository.deleteById(idTesis);
		
	}


}

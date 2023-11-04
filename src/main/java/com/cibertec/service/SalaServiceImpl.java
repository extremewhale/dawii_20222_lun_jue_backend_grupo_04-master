package com.cibertec.service;
//Jorge Quiroz
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Sala;
import com.cibertec.repository.SalaRepository;


@Service
public class SalaServiceImpl implements SalaService {

	@Autowired
	private SalaRepository repository;
	
	@Override
	public List<Sala> listarTodas() {
		return repository.findAll();
	}

	@Override
	public Sala insertar(Sala obj) {
		return repository.save(obj);
	}

	@Override
	public List<Sala> listaSalaPorAlumno(String numero, String recursos,int idSede, int estado) {
		return repository.listaSalaPorAlumno(numero, recursos, idSede, estado);
	}

	@Override
	public Sala insertaActualizaSala(Sala sala) {
		return repository.save(sala);
	}

	@Override
	public List<Sala> listaSalaPorRecursoLike(String recursos) {
		return repository.salaPorRecursoLike(recursos);
	}

	@Override
	public void eliminaSala(int idSala) {
		repository.deleteById(idSala);
		
	}

	
}
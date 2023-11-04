package com.cibertec.service;
//Davide Stagni
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Autor;
import com.cibertec.repository.AutorRepository;


@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repository;
	
	@Override
	public List<Autor> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Autor insertar(Autor obj) {
		return repository.save(obj);
	}
	
	@Override
	public Autor insertarActualizarAutor(Autor obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarAutor(int idAutor) {
		repository.deleteById(idAutor);
	}
	
	@Override
	public List<Autor> listaAutorPorFiltro(String nombres, String apellidos, String telefono, int idGrado, int estado) {
		return repository.listaAutorConFiltro(nombres, apellidos, telefono, idGrado, estado);
	}

	@Override
	public List<Autor> listaAutorPorNombresApellidosLike(String nombres, String apellidos) {
		return repository.listaAutorPorNombresApellidosLike(nombres, apellidos);
	}

	
	
	
	
}

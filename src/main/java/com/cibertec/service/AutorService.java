package com.cibertec.service;
//Davide Stagni
import java.util.List;

import com.cibertec.entity.Autor;

public interface AutorService {
	
	public abstract List<Autor> listarTodos();
	
	public abstract Autor insertar(Autor obj);

	public abstract Autor insertarActualizarAutor(Autor obj);

	public abstract void eliminarAutor(int idAutor);

	public abstract List<Autor> listaAutorPorFiltro(String nombres, String apellidos, String telefono, int idGrado, int estado);

	public abstract List<Autor> listaAutorPorNombresApellidosLike(String nombres, String apellidos);
	
}

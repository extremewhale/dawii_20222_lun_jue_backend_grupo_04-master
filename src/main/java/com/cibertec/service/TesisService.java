package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Tesis;

public interface TesisService {
	
	public abstract List<Tesis> listaTodos();
	public abstract Tesis insertaTesis(Tesis obj);
	public abstract List<Tesis> listaTesisPorTituloTemaAlumno(String titulo, String tema, int idAlumno, int estado);
	
	//Crud
	public abstract Tesis insertaActualizaTesis(Tesis tesis);
	public abstract List<Tesis> listaTesisPorTituloLike(String titulo);
	public abstract void eliminaTesis(int idTesis);

}

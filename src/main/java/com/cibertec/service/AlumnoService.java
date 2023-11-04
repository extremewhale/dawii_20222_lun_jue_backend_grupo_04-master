package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Alumno;


public interface AlumnoService {

	public abstract List<Alumno> listaTodos();
	
	public abstract List<Alumno> listaAlumno();
	public abstract Alumno insertaActualizaAlumno(Alumno obj);


}

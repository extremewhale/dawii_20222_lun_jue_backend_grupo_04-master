package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Alumno;
import com.cibertec.entity.Prueba;
import com.cibertec.repository.AlumnoRepository;

@Service
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoRepository Repository;

	@Override
	public List<Alumno> listaTodos() {
		return Repository.findAll();

	}

	@Override
	public List<Alumno> listaAlumno() {
		// TODO Auto-generated method stub
		return Repository.findAll();
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		// TODO Auto-generated method stub
		return Repository.save(obj);
	}

}

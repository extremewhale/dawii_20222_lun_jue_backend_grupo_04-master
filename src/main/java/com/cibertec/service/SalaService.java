package com.cibertec.service;
//Jorge Quiroz
import java.util.List;

import com.cibertec.entity.Sala;



public interface SalaService {
	public abstract List<Sala> listarTodas();
	public abstract Sala insertar(Sala obj);
	public abstract List<Sala> listaSalaPorAlumno(String numero, String recursos,int idSede, int estado);

	//Para el Crud
		public abstract Sala insertaActualizaSala(Sala sala);
		public abstract List<Sala> listaSalaPorRecursoLike(String recursos);
		public abstract void eliminaSala(int idSala);


}

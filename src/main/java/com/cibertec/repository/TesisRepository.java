package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Tesis;

public interface TesisRepository extends JpaRepository<Tesis, Integer>{

	@Query("select x from Tesis x where (x.titulo like ?1) and (?2 is '' or x.tema=?2) and (?3 is -1 or x.alumno.idAlumno =?3) and (x.estado = ?4) ")       
	public List<Tesis> listaTesisPorTituloTemaAlumno(String titulo, String tema, int idAlumno, int estado);


	@Query("select x from Tesis x where x.titulo like ?1")
	public List<Tesis> listaPorTituloLike(String titulo);
	
}

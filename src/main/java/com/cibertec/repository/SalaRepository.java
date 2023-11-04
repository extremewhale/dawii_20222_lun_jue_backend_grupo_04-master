package com.cibertec.repository;
import java.util.List;

//Jorge Quiroz
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Sala;


public interface SalaRepository extends JpaRepository<Sala, Integer>  {

	@Query("select x from Sala x where (x.numero like ?1) and (?2 is '' or x.recursos=?2) and (?3 is -1 or x.sede.idSede =?3) and (x.estado = ?4) ")       
	public List<Sala> listaSalaPorAlumno(String numero, String recursos, int idSede, int estado);

	@Query("select x from Sala x where x.recursos like ?1")
	public List<Sala> salaPorRecursoLike(String recursos);
	
	
	
	
}

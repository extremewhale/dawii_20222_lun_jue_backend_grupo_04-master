package com.cibertec.repository;
import java.util.List;

// Davide Stagni
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	
	@Query("select x from Autor x where (?1 is '' or x.nombres like ?1) and (?2 is '' or x.apellidos like ?2) and (?3 is '' or x.telefono like ?3) and (?4 is -1 or x.grado.idGrado =?4) and (x.estado = ?5) ")       
	public List<Autor> listaAutorConFiltro(String nombres, String apellidos, String telefono, int idGrado, int estado);

	@Query("select x from Autor x where (?1 is '' or x.nombres like ?1) and (?2 is '' or x.apellidos like ?2)")
	public List<Autor> listaAutorPorNombresApellidosLike(String nombres, String Apellidos);
}

package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {

	@Query("select e from Proveedor e where (?1 is '' or e.razonsocial like ?1) and (?2 is '' or e.ruc like ?2) and (e.estado = ?3) and (?4 is -1 or e.pais.idPais = ?4)")
	public abstract List<Proveedor> listaPorFiltro(String razonsocial, String ruc, int estado, int idPais);

	@Query("select x from Proveedor x where x.contacto like ?1")
	public List<Proveedor> listaPorContactoLike(String contacto);
	
} 

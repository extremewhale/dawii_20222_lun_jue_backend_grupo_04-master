package com.cibertec.service;

import java.util.List;

import javax.validation.Valid;

import com.cibertec.entity.Proveedor;


public interface ProveedorService {

	List<Proveedor> listaProveedor();

	Proveedor insertaActualizaProveedor(@Valid Proveedor obj);

 

	public abstract List<Proveedor> listaPorFiltro(String razonsocial, String ruc, int estado, int idPais); 
	
	
	// Crud
		public abstract Proveedor insertaActualizaProveedor2(Proveedor proveedor);
		public abstract List<Proveedor> listaProveedorPorNombreLike(String nombre);
		public abstract void eliminaProveedor(int idProveedor);
	
	
} 
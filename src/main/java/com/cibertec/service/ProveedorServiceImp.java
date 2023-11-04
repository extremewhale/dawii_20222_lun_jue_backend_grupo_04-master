package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Proveedor;
import com.cibertec.repository.ProveedorRepository;

@Service
public class ProveedorServiceImp implements ProveedorService {

	@Autowired
	private ProveedorRepository repository;

	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		return repository.save(obj);
	}

	@Override
	public List<Proveedor> listaProveedor() {
		return repository.findAll();
	}

	@Override
	public List<Proveedor> listaPorFiltro(String razonsocial, String ruc, int estado, int idPais) {

		return repository.listaPorFiltro(razonsocial, ruc, estado, idPais);
	}

	@Override
	public Proveedor insertaActualizaProveedor2(Proveedor proveedor) {
		return repository.save(proveedor);
	}

	@Override
	public List<Proveedor> listaProveedorPorNombreLike(String contacto) {
		return repository.listaPorContactoLike(contacto);
	}

	@Override
	public void eliminaProveedor(int idProveedor) {
		repository.deleteById(idProveedor);

	}

}
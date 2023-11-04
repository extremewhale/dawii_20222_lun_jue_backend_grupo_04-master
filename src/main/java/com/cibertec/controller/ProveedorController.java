package com.cibertec.controller;
//ALEJO CAMPOS
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.entity.Pais;
import com.cibertec.entity.Proveedor;
import com.cibertec.service.PaisService;
import com.cibertec.service.ProveedorService;
import com.cibertec.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN) 
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private PaisService paisService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedor() {
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/paises")
	public ResponseEntity<List<Pais>> listaPaises() {
		List<Pais> lista = paisService.listaTodos();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Proveedor obj, Errors errors) {
		HashMap<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<String>();
		salida.put("errores", lstMensajes);

		List<ObjectError> lstErrors = errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}

		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}

		obj.setEstado(1);
		obj.setFechaRegistro(new Date());
		Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		} else {
			lstMensajes.add("Se registró el proveedor con el ID ==> " + objSalida.getIdProveedor());
		}
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/listaPorFiltro")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaPorFiltro(
			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais) {

		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaPorFiltro("%" + razonsocial + "%", "%" + ruc + "%", estado,
					idPais);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen Registros");
			} else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " Registros");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);

	}

}

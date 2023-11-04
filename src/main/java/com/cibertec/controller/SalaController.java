package com.cibertec.controller;
//Jorge Quiroz
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

import com.cibertec.entity.Sala;
import com.cibertec.service.SalaService;
import com.cibertec.util.AppSettings;


@RestController
@RequestMapping("/url/sala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sala>> listarTodas(){
		List<Sala> lista = salaService.listarTodas();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> insertar(@Valid @RequestBody Sala obj, Errors errors){
		
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		
		HashMap<String, Object> salida = new HashMap<>();
		List<String> listMensajes = new ArrayList<String>();
		salida.put("errores", listMensajes);
		
		List<ObjectError> listErrors =  errors.getAllErrors();
		for (ObjectError objectError : listErrors) {
			objectError.getDefaultMessage();
			listMensajes.add(objectError.getDefaultMessage());
		}

		if (!CollectionUtils.isEmpty(listMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		Sala objSalida = salaService.insertar(obj);
		if (objSalida == null) {
			listMensajes.add("Error en el registro de la Sala");
		}
		else {
			listMensajes.add("Se registró la Sala con el ID " + objSalida.getIdSala());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/listaSalaConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocenteNombreDniUbigeo(
			@RequestParam(name = "numero", required = false, defaultValue = "") String numero,
			@RequestParam(name = "recursos", required = false, defaultValue = "") String recursos,
			@RequestParam(name = "idSede", required = false, defaultValue = "-1") int idSede,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Sala> lista = salaService.listaSalaPorAlumno("%"+numero+"%", recursos, idSede, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","Error");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
}

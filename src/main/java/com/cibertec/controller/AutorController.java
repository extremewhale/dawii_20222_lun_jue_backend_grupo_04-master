package com.cibertec.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
// Davide Stagni
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

import com.cibertec.entity.Autor;
import com.cibertec.service.AutorService;
import com.cibertec.util.AppSettings;

@RestController
@RequestMapping("/url/autor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Autor>> listarTodos(){
		List<Autor> lista = autorService.listarTodos();
		return ResponseEntity.ok(lista);
	}


	@PostMapping
	@ResponseBody
	public ResponseEntity<?> insertar(@Valid @RequestBody Autor obj, Errors errors){
		
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
		
		Autor objSalida = autorService.insertar(obj);
		if (objSalida == null) {
			listMensajes.add("Error en el registro");
		}
		else {
			listMensajes.add("Se registró el autor con el ID " + objSalida.getIdAutor());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/listaAutorConFiltro")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAutorConFiltro(
			@RequestParam(name = "nombres", 	required = false, defaultValue = "") 	String nombres,
			@RequestParam(name = "apellidos", 	required = false, defaultValue = "") 	String apellidos,
			@RequestParam(name = "telefono", 	required = false, defaultValue = "") 	String telefono,
			@RequestParam(name = "idGrado", 	required = false, defaultValue = "-1") 	int idGrado,
			@RequestParam(name = "estado", 		required = true, defaultValue = "1") 	int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Autor> lista = autorService.listaAutorPorFiltro("%"+nombres+"%", "%"+apellidos+"%", "%"+telefono+"%", idGrado, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			} 
			else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","Error en la consulta");
		}
		return ResponseEntity.ok(salida);
	}

}

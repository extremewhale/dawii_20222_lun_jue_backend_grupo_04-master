package com.cibertec.controller;
import java.util.Date;
import java.util.HashMap;
// Davide Stagni
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.entity.Autor;
import com.cibertec.service.AutorService;
import com.cibertec.util.AppSettings;
import com.cibertec.util.Constantes;

@RestController
@RequestMapping("/url/autor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN) //(origins = "http://localhost:4200")
public class CrudAutorController {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/listaAutorPorNombresApellidosLike")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAutorPorNombresLike(
			@RequestParam(name = "nombres",   required = false, defaultValue = "") 	String nombres,
			@RequestParam(name = "apellidos", required = false, defaultValue = "") 	String apellidos
	) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Autor> lista = autorService.listaAutorPorNombresApellidosLike("%"+nombres+"%", "%"+apellidos+"%");
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
	
	@PostMapping("/registraAutor")
	@ResponseBody
	
	public ResponseEntity<Map<String, Object>> insertarAutor(@RequestBody Autor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdAutor(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Autor objSalida =  autorService.insertarActualizarAutor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} 
			else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizarAutor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizarAutor(@RequestBody Autor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Autor objSalida =  autorService.insertarActualizarAutor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} 
			else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaAutor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarAutor(@PathVariable("id") int idAutor) {
		Map<String, Object> salida = new HashMap<>();
		try {
			autorService.eliminarAutor(idAutor);
			salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}


	
}

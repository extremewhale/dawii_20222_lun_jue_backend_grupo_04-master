package com.cibertec.controller;

import java.util.Date;
import java.util.HashMap;
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

import com.cibertec.entity.Sala;
import com.cibertec.service.SalaService;
import com.cibertec.util.Constantes;



	@RestController
	@RequestMapping("/url/sala")
	@CrossOrigin(origins = "http://localhost:4200")
	public class CrudSalaController {

		@Autowired
		private SalaService service;
		
		@GetMapping("/listaSalaPorRecursoLike")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaSalaRecurso(
				@RequestParam(name = "recurso", required = false, defaultValue = "") String recurso){
			Map<String, Object> salida = new HashMap<>();
			try {
				List<Sala> lista = service.listaSalaPorRecursoLike("%"+recurso+"%");
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
		
		
		@PostMapping("/registraSala")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> insertaSala(@RequestBody Sala obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				obj.setIdSala(0);
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Sala objSalida =  service.insertaActualizaSala(obj);
				if (objSalida == null) {
					salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
				} else {
					salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}
			return ResponseEntity.ok(salida);
		}

		@PutMapping("/actualizaSala")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaSala(@RequestBody Sala obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				Sala objSalida =  service.insertaActualizaSala(obj);
				if (objSalida == null) {
					salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
				} else {
					salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		
		@DeleteMapping("/eliminaSala/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaSala(@PathVariable("id") int idSala) {
			Map<String, Object> salida = new HashMap<>();
			try {
				service.eliminaSala(idSala);
				salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
	
	}


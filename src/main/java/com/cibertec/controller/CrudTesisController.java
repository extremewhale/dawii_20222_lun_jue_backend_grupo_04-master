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

import com.cibertec.entity.Tesis;
import com.cibertec.service.TesisService;
import com.cibertec.util.Constantes;

@RestController
@RequestMapping("/url/tesis")
@CrossOrigin(origins = "http://localhost:4200")
public class CrudTesisController {
	

		@Autowired
		private TesisService service;
		
		
		@GetMapping("/listaTesisPorTituloLike")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaTesisTitulo(
				@RequestParam(name = "titulo", required = false, defaultValue = "") String titulo ){
			Map<String, Object> salida = new HashMap<>();
			try {
				List<Tesis> lista = service.listaTesisPorTituloLike("%"+titulo+"%");
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
		
		@PostMapping("/registraTesis")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> insertaTesis(@RequestBody Tesis obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				obj.setIdTesis(0);
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Tesis objSalida =  service.insertaActualizaTesis(obj);
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

		@PutMapping("/actualizaTesis")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaTesis(@RequestBody Tesis obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				Tesis objSalida =  service.insertaActualizaTesis(obj);
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
		
		
		@DeleteMapping("/eliminaTesis/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaTesis(@PathVariable("id") int idTesis) {
			Map<String, Object> salida = new HashMap<>();
			try {
				service.eliminaTesis(idTesis);
				salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}

	}
	


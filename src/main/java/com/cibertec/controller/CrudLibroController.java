package com.cibertec.controller;

import com.cibertec.entity.Libro;
import com.cibertec.service.LibroService;
import com.cibertec.util.AppSettings;
import com.cibertec.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url/crud/libro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudLibroController {

    @Autowired
    private LibroService service;

    @GetMapping("/listaLibroPorTitulo/{titulo}")
    @ResponseBody
    public ResponseEntity<List<Libro>> listaLibroPorLike(@PathVariable("titulo") String tituloLibro) {
        List<Libro> lista  = null;
        try {
            if (tituloLibro.equals("todos")) {
                lista = service.listLibro();
            }else {
                lista = service.listaLibroPorTitulo("%" + tituloLibro + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registraLibro")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertaLibro(@RequestBody Libro obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            obj.setIdLibro(0);
            obj.setFechaRegistro(new Date());
            obj.setEstado(1);
            Libro objSalida =  service.insertLibro(obj);
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

    @PutMapping("/actualizaLibro")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Libro obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            Libro objSalida =  service.insertLibro(obj);
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


    @DeleteMapping("/eliminaLibro/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id") int idDocente) {
        Map<String, Object> salida = new HashMap<>();
        try {
            service.eliminaLibro(idDocente);
            salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
        }
        return ResponseEntity.ok(salida);
    }
}

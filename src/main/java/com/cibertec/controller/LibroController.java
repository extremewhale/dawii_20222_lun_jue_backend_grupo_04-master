package com.cibertec.controller;

import com.cibertec.entity.Libro;
import com.cibertec.service.LibroService;
import com.cibertec.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.cibertec.util.AppSettings;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url/libro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class LibroController {

    @Autowired
    private LibroService libroService;
    @GetMapping("/findAll")
    public ResponseEntity<List<Libro>> findAllLibro(){
        List<Libro> listLibros = libroService.listLibro();
        return new ResponseEntity<>(listLibros , HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Libro> insertLibro(@RequestBody Libro libro){
        libro.setEstado(1);
        libro.setFechaRegistro(new Date());
        Libro libroResponse = libroService.insertLibro(libro);

        return new ResponseEntity<>(libroResponse , HttpStatus.OK);
    }

    @GetMapping("/listaLibroConParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listaLibroPorCategoria(
            @RequestParam(name = "titulo", required = false, defaultValue = "") String titulo,
            @RequestParam(name = "serie", required = false, defaultValue = "") String serie,
            @RequestParam(name = "idCategoria", required = false, defaultValue = "-1") int idCategoria,
            @RequestParam(name = "estado", required = true, defaultValue = "1") int estado,
            @RequestParam(name = "fechaInicio", required = false, defaultValue = "9999-01-01") String fechaInicio,
            @RequestParam(name = "fechaFin", required = false, defaultValue = "9999-01-01") String fechaFin) {
        Map<String, Object> salida = new HashMap<>();
        try {
            List<Libro> lista = libroService.listaLibroPorCategoria(
                    "%"+titulo+"%", serie, idCategoria, estado,fechaInicio, fechaFin);
            if (CollectionUtils.isEmpty(lista)) {
                salida.put("mensaje", "No existen datos para mostrar");
            }else {
                salida.put("lista", lista);
                salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
        }
        return ResponseEntity.ok(salida);
    }

}

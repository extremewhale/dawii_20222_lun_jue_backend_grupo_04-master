package com.cibertec.service;

import com.cibertec.entity.Libro;
import com.cibertec.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listLibro() {
        return libroRepository.findAll();
    }

    @Override
    public Libro insertLibro(Libro obj) {
        return libroRepository.save(obj);
    }

    @Override
    public List<Libro> listaLibroPorCategoria(
            String titulo, String serie, int idCategoria, int estado, String fecInicio, String fecFin) {
        return libroRepository.listaLibroPorCategoria(titulo, serie, idCategoria, estado, fecInicio, fecFin);
    }

    @Override
    public List<Libro> listaLibroPorTitulo(String titulo) {
        return libroRepository.listaPorTituloLike(titulo);
    }

    @Override
    public void eliminaLibro(int idLibro) {
        libroRepository.deleteById(idLibro);
    }
}

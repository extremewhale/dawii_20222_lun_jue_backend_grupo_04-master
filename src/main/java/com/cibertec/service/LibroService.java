package com.cibertec.service;

import com.cibertec.entity.Libro;

import java.util.List;

public interface LibroService {

    public  List<Libro> listLibro();
    public  Libro insertLibro(Libro obj);
    public List<Libro> listaLibroPorCategoria(String titulo, String serie, int idCategoria, int estado, String fecInicio, String fecFin);
    public List<Libro> listaLibroPorTitulo(String titulo);
    public void eliminaLibro(int idLibro);
}

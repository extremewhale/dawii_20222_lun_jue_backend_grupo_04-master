package com.cibertec.repository;

import com.cibertec.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    @Query("select l from Libro l where (?1 is '' or l.titulo like ?1) and " +
            "(?2 is '' or l.serie = ?2) and (?3 is -1 or l.categoria.idCategoria = ?3) and " +
            "(l.estado = ?4) and " +
            "(?5 is '9999-01-01' or ?6 is '9999-01-01' or (l.fechaRegistro between ?5  and ?6 ))")
    public List<Libro> listaLibroPorCategoria(
            String titulo, String serie, int idCategoria, int estado, String fecInicio, String fecFin);

    @Query("select x from Libro x where x.titulo like ?1")
    public List<Libro> listaPorTituloLike(String titulo);
}

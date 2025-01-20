package com.alura.literalura.repository;

import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)")
    Optional<Libro> obtenerLibroPorNombre(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.lenguaje=:lenguaje")
    List<Libro> obtenerLibrosPorLenguaje(Lenguaje lenguaje);
}

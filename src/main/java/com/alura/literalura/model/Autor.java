package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        if (autor.fechaNacimiento() != null) {
            this.fechaNacimiento = autor.fechaNacimiento();
        } else {
            this.fechaNacimiento = 1900;
        }
        if (autor.fechaFallecimiento() != null) {
            this.fechaFallecimiento = autor.fechaFallecimiento();
        } else {
            this.fechaFallecimiento = 3000;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String librosNombres = libros.stream()
                                       .map(Libro::getTitulo)
                                       .reduce((titulo1, titulo2) -> titulo1 + ", " + titulo2)
                                       .orElse("No books available");
        return "Autor: " + nombre + "\nFecha de Nacimiento: " + fechaNacimiento + "\nFecha de Fallecimiento: " + fechaFallecimiento + "\nLibros: " + librosNombres + "\n";
    }
}

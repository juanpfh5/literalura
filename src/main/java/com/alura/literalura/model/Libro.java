package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Lenguaje lenguaje;
    private Integer numeroDeDescargas;
    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibro libro, Autor autor) {
        this.titulo = libro.titulo();
//        Optional<DatosAutor> autor = libro.autores().stream().findFirst();
//        if (autor.isPresent()) {
//            this.autor = new Autor(autor.get());
//        }
        Optional<String> lenguaje = libro.lenguajes().stream().findFirst();
        if (lenguaje.isPresent()) {
            this.lenguaje = Lenguaje.fromString(lenguaje.get());
        }
        this.numeroDeDescargas = libro.numeroDeDescargas();
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "-------  LIBRO -------\n" +
                "\tTítulo: " + titulo + "\n" +
                "\tAutor: [" + autor.getNombre() + "]\n" +
                "\tLenguaje: " + lenguaje + "\n" +
                "\tNúmero de descargas: " + numeroDeDescargas + "\n" +
                "---------------------\n\n";
    }
}

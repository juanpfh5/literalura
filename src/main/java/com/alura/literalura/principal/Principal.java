package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public void mostrarMenu(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        String opcion;

        System.out.println("\nBienvenido a LiterAlura");
        do {
            System.out.print("""
                    \t---------------------
                    \t1.- Buscar libro por título
                    \t2.- Listar libros registrados
                    \t3.- Listar autores registrados
                    \t4.- Listar autores vivos en un determinado año
                    \t5.- Listar libros por lenguaje
                    \t0.- Salir
                    \t> \s""");
            opcion = scan.nextLine();
            System.out.println();

            switch (opcion) {
                case "1":
                    buscarLibroPorTitulo();
                    break;
                case "2":
                    listarLibrosRegistrados();
                    break;
                case "3":
                    listarAutoresRegistrados();
                    break;
                case "4":
                    listarAutoresVivosEnAnio();
                    break;
                case "5":
                    listarLibrosPorLenguaje();
                    break;
                case "0":
                    System.out.println("Saliendo de LiterAlura...");
                    break;
                default:
                    System.out.println("ERROR: Opción inválida\n");
            }
        } while (!opcion.equals("0"));
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = scan.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));

        JSONObject jsonObject = new JSONObject(json);
        JSONArray results = jsonObject.getJSONArray("results");
        if (results.isEmpty()) {
            System.out.println("No se encontraron resultados");
            return;
        }

        DatosLibro datosLibro = conversor.obtenerDatos(results.getJSONObject(0).toString(), DatosLibro.class);

        Optional<Libro> libro = libroRepository.obtenerLibroPorNombre(datosLibro.titulo());
        if (libro.isPresent()) {
            System.out.println("El libro ya se encuentra registrado");
            return;
        }

        Optional<Autor> autor = autorRepository.obtenerAutorPorNombre(datosLibro.autores().get(0).nombre());
        if (autor.isEmpty()) {
            autor = Optional.of(new Autor(datosLibro.autores().get(0)));
            autorRepository.save(autor.get());
        } else {
            autor = autorRepository.obtenerAutorPorNombre(datosLibro.autores().get(0).nombre());
        }

        Libro libroNuevo = new Libro(datosLibro, autor.get());
        libroRepository.save(libroNuevo);
        System.out.println(libroNuevo);
    }

    private void listarLibrosRegistrados() {
        if (libroRepository.findAll().isEmpty()){
            System.out.println("No hay libros registrados\n");
        } else {
            libroRepository.findAll().forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        if (autorRepository.findAll().isEmpty()){
            System.out.println("No hay autores registrados\n");
        } else {
            autorRepository.findAll().forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAnio() {
        String anio;
        System.out.println("Ingrese el año: ");
        anio = scan.nextLine();
        System.out.println();

        if (anio.matches("[0-9]{4}")) {
            autorRepository.obtenerAutoresVivosEnAnio(Integer.parseInt(anio)).forEach(System.out::println);
        } else {
            System.out.println("ERROR: Año inválido\n");
        }
    }

    private void listarLibrosPorLenguaje() {
        System.out.println("Ingrese el lenguaje: ");
        System.out.println("\tLenguajes disponibles: ");
        Lenguaje.listarLenguajes();
        String lenguaje = scan.nextLine();
        try {
            Lenguaje.valueOf(lenguaje);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (libroRepository.obtenerLibrosPorLenguaje(Lenguaje.valueOf(lenguaje)).isEmpty()) {
            System.out.println("No hay libros registrados en ese lenguaje\n");
            return;
        }

        libroRepository.obtenerLibrosPorLenguaje(Lenguaje.valueOf(lenguaje)).forEach(System.out::println);
    }
}

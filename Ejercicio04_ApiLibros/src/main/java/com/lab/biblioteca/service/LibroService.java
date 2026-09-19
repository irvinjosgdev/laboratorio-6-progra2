package com.lab.biblioteca.service;

import com.lab.biblioteca.model.Libro;

import java.util.List;

public interface LibroService {

    List<Libro> listarLibros();

    Libro buscarPorTitulo(String titulo);

    Libro registrar(Libro libro);

    Libro actualizar(Long id, Libro datos);

    void eliminar(Long id);
}

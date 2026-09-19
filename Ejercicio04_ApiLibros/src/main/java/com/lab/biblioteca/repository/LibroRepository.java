package com.lab.biblioteca.repository;

import com.lab.biblioteca.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {

    List<Libro> findAll();

    Optional<Libro> findById(Long id);

    Optional<Libro> findByTitulo(String titulo);

    Libro save(Libro libro);

    boolean deleteById(Long id);
}

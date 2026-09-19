package com.lab.biblioteca.repository;

import com.lab.biblioteca.model.EstadoLibro;
import com.lab.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LibroRepositoryImpl implements LibroRepository {

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong();

    public LibroRepositoryImpl() {
        save(new Libro(null, "Cien anios de soledad", "Gabriel Garcia Marquez", "978-0307474728", 1967, EstadoLibro.DISPONIBLE));
        save(new Libro(null, "El principito", "Antoine de Saint-Exupery", "978-0156012195", 1943, EstadoLibro.PRESTADO));
        save(new Libro(null, "Clean Code", "Robert C. Martin", "978-0132350884", 2008, EstadoLibro.DISPONIBLE));
    }

    @Override
    public List<Libro> findAll() {
        return libros;
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Libro> findByTitulo(String titulo) {
        return libros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(contadorId.incrementAndGet());
            libros.add(libro);
        }
        return libro;
    }

    @Override
    public boolean deleteById(Long id) {
        return libros.removeIf(libro -> libro.getId().equals(id));
    }
}

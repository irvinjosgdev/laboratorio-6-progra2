package com.lab.biblioteca.controller;

import com.lab.biblioteca.model.Libro;
import com.lab.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> consultarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Libro> consultarLibroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<Libro> registrarLibro(@Valid @RequestBody Libro libro) {
        Libro libroRegistrado = libroService.registrar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroRegistrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @Valid @RequestBody Libro datos) {
        return ResponseEntity.ok(libroService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

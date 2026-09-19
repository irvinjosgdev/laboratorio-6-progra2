package com.lab.biblioteca.service;

import com.lab.biblioteca.exception.LibroNoEncontradoException;
import com.lab.biblioteca.model.Libro;
import com.lab.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new LibroNoEncontradoException("No existe un libro con el titulo: " + titulo));
    }

    @Override
    public Libro registrar(Libro libro) {
        libro.setId(null);
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro datos) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("No existe un libro con el id: " + id));
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setIsbn(datos.getIsbn());
        libro.setAnioPublicacion(datos.getAnioPublicacion());
        libro.setEstado(datos.getEstado());
        return libro;
    }

    @Override
    public void eliminar(Long id) {
        boolean eliminado = libroRepository.deleteById(id);
        if (!eliminado) {
            throw new LibroNoEncontradoException("No existe un libro con el id: " + id);
        }
    }
}

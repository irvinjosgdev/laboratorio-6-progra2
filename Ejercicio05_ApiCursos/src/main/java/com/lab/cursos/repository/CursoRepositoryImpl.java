package com.lab.cursos.repository;

import com.lab.cursos.model.Curso;
import com.lab.cursos.model.EstadoCurso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong();

    public CursoRepositoryImpl() {
        save(new Curso(null, "Estructuras de Datos", "CC-201", 4, EstadoCurso.ACTIVO));
        save(new Curso(null, "Bases de Datos", "CC-210", 3, EstadoCurso.ACTIVO));
        save(new Curso(null, "Calculo I", "MAT-101", 5, EstadoCurso.INACTIVO));
    }

    @Override
    public List<Curso> findAll() {
        return cursos;
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Curso> findByCodigo(String codigo) {
        return cursos.stream()
                .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    @Override
    public Curso save(Curso curso) {
        if (curso.getId() == null) {
            curso.setId(contadorId.incrementAndGet());
            cursos.add(curso);
        }
        return curso;
    }

    @Override
    public boolean deleteById(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }
}

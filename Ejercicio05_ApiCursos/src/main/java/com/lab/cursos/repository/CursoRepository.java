package com.lab.cursos.repository;

import com.lab.cursos.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {

    List<Curso> findAll();

    Optional<Curso> findById(Long id);

    Optional<Curso> findByCodigo(String codigo);

    Curso save(Curso curso);

    boolean deleteById(Long id);
}

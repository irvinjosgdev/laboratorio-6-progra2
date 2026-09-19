package com.lab.cursos.service;

import com.lab.cursos.model.Curso;

import java.util.List;

public interface CursoService {

    List<Curso> listarCursos();

    Curso buscarPorCodigo(String codigo);

    Curso crear(Curso curso);

    Curso actualizar(Long id, Curso datos);

    void eliminar(Long id);
}

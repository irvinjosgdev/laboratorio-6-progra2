package com.lab.cursos.service;

import com.lab.cursos.exception.CursoNoEncontradoException;
import com.lab.cursos.model.Curso;
import com.lab.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso buscarPorCodigo(String codigo) {
        return cursoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new CursoNoEncontradoException("No existe un curso con el codigo: " + codigo));
    }

    @Override
    public Curso crear(Curso curso) {
        curso.setId(null);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Long id, Curso datos) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNoEncontradoException("No existe un curso con el id: " + id));
        curso.setNombre(datos.getNombre());
        curso.setCodigo(datos.getCodigo());
        curso.setCreditos(datos.getCreditos());
        curso.setEstado(datos.getEstado());
        return curso;
    }

    @Override
    public void eliminar(Long id) {
        boolean eliminado = cursoRepository.deleteById(id);
        if (!eliminado) {
            throw new CursoNoEncontradoException("No existe un curso con el id: " + id);
        }
    }
}

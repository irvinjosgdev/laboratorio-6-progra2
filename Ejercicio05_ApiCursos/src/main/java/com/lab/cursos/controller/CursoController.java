package com.lab.cursos.controller;

import com.lab.cursos.model.Curso;
import com.lab.cursos.service.CursoService;
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
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> consultarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Curso> consultarCursoPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(cursoService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        Curso cursoCreado = cursoService.crear(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @Valid @RequestBody Curso datos) {
        return ResponseEntity.ok(cursoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

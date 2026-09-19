package com.lab.cursos.exception;

public class CursoNoEncontradoException extends RuntimeException {

    public CursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

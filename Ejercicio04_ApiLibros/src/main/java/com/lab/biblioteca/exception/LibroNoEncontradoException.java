package com.lab.biblioteca.exception;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

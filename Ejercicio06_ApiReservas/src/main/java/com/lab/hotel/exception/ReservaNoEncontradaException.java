package com.lab.hotel.exception;

public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

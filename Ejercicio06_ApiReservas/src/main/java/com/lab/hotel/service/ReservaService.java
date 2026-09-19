package com.lab.hotel.service;

import com.lab.hotel.model.Reserva;

import java.util.List;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva buscarPorId(Long id);

    Reserva crear(Reserva reserva);

    Reserva actualizar(Long id, Reserva datos);

    Reserva cancelar(Long id);
}

package com.lab.hotel.repository;

import com.lab.hotel.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {

    List<Reserva> findAll();

    Optional<Reserva> findById(Long id);

    Reserva save(Reserva reserva);
}

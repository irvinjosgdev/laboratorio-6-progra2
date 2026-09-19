package com.lab.hotel.service;

import com.lab.hotel.exception.ReservaInvalidaException;
import com.lab.hotel.exception.ReservaNoEncontradaException;
import com.lab.hotel.exception.ReservaYaCanceladaException;
import com.lab.hotel.model.EstadoReserva;
import com.lab.hotel.model.Reserva;
import com.lab.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("No existe una reserva con el id: " + id));
    }

    @Override
    public Reserva crear(Reserva reserva) {
        validarFechas(reserva);
        reserva.setId(null);
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva actualizar(Long id, Reserva datos) {
        validarFechas(datos);
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("No existe una reserva con el id: " + id));
        reserva.setNombreCliente(datos.getNombreCliente());
        reserva.setHabitacion(datos.getHabitacion());
        reserva.setFechaEntrada(datos.getFechaEntrada());
        reserva.setFechaSalida(datos.getFechaSalida());
        reserva.setEstado(datos.getEstado());
        return reserva;
    }

    @Override
    public Reserva cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("No existe una reserva con el id: " + id));
        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReservaYaCanceladaException("La reserva con id " + id + " ya se encuentra cancelada");
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        return reserva;
    }

    private void validarFechas(Reserva reserva) {
        if (!reserva.getFechaSalida().isAfter(reserva.getFechaEntrada())) {
            throw new ReservaInvalidaException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
    }
}

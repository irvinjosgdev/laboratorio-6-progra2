package com.lab.hotel.repository;

import com.lab.hotel.model.EstadoReserva;
import com.lab.hotel.model.Reserva;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong();

    public ReservaRepositoryImpl() {
        save(new Reserva(null, "Maria Lopez", "101", LocalDate.of(2026, 9, 20), LocalDate.of(2026, 9, 25), EstadoReserva.CONFIRMADA));
        save(new Reserva(null, "Carlos Perez", "204", LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 3), EstadoReserva.PENDIENTE));
        save(new Reserva(null, "Ana Gutierrez", "305", LocalDate.of(2026, 9, 15), LocalDate.of(2026, 9, 18), EstadoReserva.CANCELADA));
    }

    @Override
    public List<Reserva> findAll() {
        return reservas;
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }

    @Override
    public Reserva save(Reserva reserva) {
        if (reserva.getId() == null) {
            reserva.setId(contadorId.incrementAndGet());
            reservas.add(reserva);
        }
        return reserva;
    }
}

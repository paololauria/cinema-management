package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(long userId);
    List<Reservation> findByFilmProjectionIdEquals(long projectionId);
    Optional<Reservation> findByFilmProjectionIdEqualsAndReservedSeatEquals(long projectionId, long seatId);
}

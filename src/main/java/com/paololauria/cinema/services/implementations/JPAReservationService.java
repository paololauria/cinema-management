package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.ReservationRequest;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Reservation;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.BusinessLogicExeception;
import com.paololauria.cinema.model.exceptions.DuplicateReservationException;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.model.repository.abstractions.FilmProjectionRepository;
import com.paololauria.cinema.model.repository.abstractions.ReservationRepository;
import com.paololauria.cinema.services.abstraction.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
@Service
public class JPAReservationService implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final FilmProjectionRepository filmProjectionRepository;
    public JPAReservationService(ReservationRepository reservationRepository, FilmProjectionRepository filmProjectionRepository) {
        this.reservationRepository = reservationRepository;
        this.filmProjectionRepository = filmProjectionRepository;
    }
    @Override
    @Transactional
    public Reservation createReservation(ReservationRequest reservationRequest, User user) throws EntityNotFoundException, DuplicateReservationException, BusinessLogicExeception {
        Optional<FilmProjection> ot = filmProjectionRepository.findById(reservationRequest.getIdProjection());
        System.out.println(reservationRequest.getIdProjection());
        if (ot.isEmpty()){
            throw new EntityNotFoundException("Tentativo di prenotare per una proiezione inesistente", FilmProjection.class);
        }
        FilmProjection filmProjection = ot.get();
        if (reservationRequest.getSeatNumber() > 0 && reservationRequest.getSeatNumber() > filmProjection.getHall().getTotalSeatNumber()){
            throw new BusinessLogicExeception
                    (String.format("Posto inesistente, hall id:%d seat id: %d",
                            filmProjection.getHallId(), reservationRequest.getSeatNumber()));
        }
        Optional<Reservation> result = reservationRepository.findByFilmProjectionIdEqualsAndReservedSeatEquals(reservationRequest.getIdProjection(),
                reservationRequest.getSeatNumber());
        if (result.isPresent()){
            throw new DuplicateReservationException("Tentativo di prenotare un posto già occupato",
                    reservationRequest.getSeatNumber(),
                    reservationRequest.getIdProjection(),
                    reservationRequest.isDiscount());
        }
        Reservation reservation = new Reservation(0,user, ot.get(),
                reservationRequest.getSeatNumber(), LocalDate.now(), LocalTime.now(), reservationRequest.isDiscount());
        reservationRepository.save(reservation);
        return reservation;
    }
}
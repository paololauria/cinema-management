package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.ReservationRequest;
import com.paololauria.cinema.dtos.ProjectionReservationDto;
import com.paololauria.cinema.dtos.ReservationDto;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Reservation;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.BusinessLogicExeception;
import com.paololauria.cinema.model.exceptions.DuplicateReservationException;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.services.abstraction.CinemaService;
import com.paololauria.cinema.services.abstraction.FilmProjectionService;
import com.paololauria.cinema.services.abstraction.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {
CinemaService cinemaService;
ReservationService reservationService;
FilmProjectionService filmProjectionService;
    @Autowired
    public ReservationController(CinemaService cinemaService,
                                 ReservationService reservationService,
                                 FilmProjectionService filmProjectionService) {
        this.cinemaService = cinemaService;
        this.reservationService = reservationService;
        this.filmProjectionService = filmProjectionService;
    }

    /**
     * Creates a new reservation.
     *
     * @param reservationRequest The reservation request.
     * @param user               The authenticated user.
     * @return ResponseEntity with the created reservation or a bad request with an error message.
     * @throws URISyntaxException If there is an issue with URI creation.
     */
    @PostMapping("/")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequest, @AuthenticationPrincipal User user) throws URISyntaxException {
        try {
            Reservation res = reservationService.createReservation(reservationRequest, user);
            URI location = new URI("/api/reservation/" + res.getId());
            ReservationDto created = new ReservationDto(res);
            return ResponseEntity.created(location).body(created);
        } catch (DuplicateReservationException | BusinessLogicExeception | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

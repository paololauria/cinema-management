package com.paololauria.cinema.services.abstraction;
import com.paololauria.cinema.ReservationRequest;
import com.paololauria.cinema.model.entities.Reservation;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.BusinessLogicExeception;
import com.paololauria.cinema.model.exceptions.DuplicateReservationException;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
public interface ReservationService {
    Reservation createReservation(ReservationRequest reservationRequest, User user) throws EntityNotFoundException, DuplicateReservationException, BusinessLogicExeception;
}

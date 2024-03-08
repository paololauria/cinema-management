package com.paololauria.cinema.services.abstraction;
import com.paololauria.cinema.model.entities.*;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;

import java.util.List;
public interface CinemaService {
    List<Actor> findAllActors();
    List<Actor> findActorsByFilmId(long filmId);

    List<Genre> findAllGenres();


    List<Hall> findAllHalls();

    Hall findHallById(long hallId);
    Hall findHallByProjectionId(long projectionId) throws EntityNotFoundException;
    Hall getRandomHall(List<Hall> halls);

    List<Reservation> findReservationByProjection(long projectionId);
    List<Reservation> findReservationByUser(Long userId);


    List<Feedback> findFeedbackByUser(Long userId);
    void createFeedback(Feedback feedback, User user);
    List<Feedback> findAllFeedbackByFilmId(long filmId);


}

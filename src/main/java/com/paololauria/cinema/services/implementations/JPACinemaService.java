package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.model.entities.*;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.model.repository.abstractions.*;
import com.paololauria.cinema.services.abstraction.CinemaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class JPACinemaService implements CinemaService {
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final HallRepository hallRepository;
    private final ReservationRepository reservationRepository;
    private final FeedbackRepository feedbackRepository;
    private final FilmProjectionRepository filmProjectionRepository;

    public JPACinemaService(ActorRepository actorRepository,
                            HallRepository hallRepository,
                            GenreRepository genreRepository,
                            ReservationRepository reservationRepository,
                            FeedbackRepository feedbackRepository,
                            FilmProjectionRepository filmProjectionRepository) {
        this.actorRepository = actorRepository;
        this.hallRepository = hallRepository;
        this.genreRepository = genreRepository;
        this.reservationRepository = reservationRepository;
        this.feedbackRepository = feedbackRepository;
        this.filmProjectionRepository = filmProjectionRepository;
    }

    @Override
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }
    @Override
    public List<Actor> findActorsByFilmId(long filmId) {
        return actorRepository.findByFilmIdEquals(filmId);
    }


    @Override
    public List<Feedback> findAllFeedbackByFilmId(long filmId) {
        return feedbackRepository.findByFilmId(filmId);
    }
    @Override
    public List<Feedback> findFeedbackByUser(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }
    @Override
    public void createFeedback(Feedback feedback, User user) {
        feedbackRepository.save(feedback);
    }


    @Override
    public List<Reservation> findReservationByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
    @Override
    public List<Reservation> findReservationByProjection(long projectionId) {
        return reservationRepository.findByFilmProjectionIdEquals(projectionId);
    }


    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }


    @Override
    public List<Hall> findAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getRandomHall(List<Hall> halls) {
        Random random = new Random();
        return halls.get(random.nextInt(halls.size()));
    }

    @Override
    public Hall findHallById(long hallId) {
        Optional<Hall> ot = hallRepository.findById(hallId);
        return ot.orElse(null);
    }
    @Override
    public Hall findHallByProjectionId(long projectionId) throws EntityNotFoundException {
        Optional<FilmProjection> filmProjection = filmProjectionRepository.findById(projectionId);
        if(filmProjection.isEmpty()){
            throw new EntityNotFoundException("Tentativo di ricerca sala su una proiezione inesistente", FilmProjection.class);
        }
        return hallRepository.findByProjectionId(projectionId);
    }
}
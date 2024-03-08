package com.paololauria.cinema.services.abstraction;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Hall;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface FilmProjectionService {
    FilmProjection createNewProjection(Film film, LocalDate date);
    List<FilmProjection> findAllProjections();
    List<FilmProjection> getPastProjections();
    List<FilmProjection> getFilmProjectionForToday();
    List<FilmProjection> getFilmProjectionByFilmAndDate(Long filmId, LocalDate date);
    List<FilmProjection> getFilmProjectionNextWeek();
    List<FilmProjection> findAll();
    List<FilmProjection> findAllProjectionByFilmId(long filmId);
    Optional<FilmProjection> findFilmProjectionById(Long filmProjectionId);
    void createProjection(FilmProjection projection, User user);
    void deleteProjectionById(long projectionId, User user);
    void updateProjection(FilmProjection projection, User user);
    void generateDailyProjections();
}

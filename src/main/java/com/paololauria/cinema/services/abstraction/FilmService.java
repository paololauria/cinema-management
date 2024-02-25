package com.paololauria.cinema.services.abstraction;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.User;
import java.util.List;
public interface FilmService {
    List<Film> findAllFilms();
    List<Film> findFilmsByGenre(String genre);
    List<Film> findTop10Films();
    List<Film> searchByTitle(String title);
    List<Film> getSearchSuggestion(String term);
    Film findFilmById(Long filmId);
    Film findFilmByProjectionId(long projectionId);
    void createFilm(Film film);
    void deleteFilmById(long filmId, User user);
    void updateFilm(Film film, User user);
}

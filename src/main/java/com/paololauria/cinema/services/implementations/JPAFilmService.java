package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.dtos.FilmDetailsDto;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.repository.abstractions.FilmProjectionRepository;
import com.paololauria.cinema.model.repository.abstractions.FilmRepository;
import com.paololauria.cinema.services.abstraction.FilmService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JPAFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final FilmProjectionRepository filmProjectionRepository;
    public JPAFilmService(FilmRepository filmRepository, FilmProjectionRepository filmProjectionRepository) {
        this.filmRepository = filmRepository;
        this.filmProjectionRepository = filmProjectionRepository;
    }
    @Override
    public void createFilm(Film film) {
        filmRepository.save(film);
    }
    @Override
    public void deleteFilmById(long filmId, User user) {
        filmRepository.deleteById(filmId);
    }
    @Override
    public void updateFilm(Film film, User user) {
        filmRepository.save(film);
    }


    @Override
    public List<Film> searchByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }
    @Override
    public List<Film> getSearchSuggestion(String term) {
        return filmRepository.findByTitleContainingIgnoreCase(term);
    }
    @Override
    public List<Film> findTop10Films() {
        return filmRepository.findTop10ByAverage();
    }
    @Override
    public List<Film> findFilmsByGenre(String genre) {
        return filmRepository.findByGenresGenreName(genre);
    }
    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }


    @Override
    public Film findFilmByProjectionId(long projectionId) {
        Optional<FilmProjection> projection = filmProjectionRepository.findById(projectionId);
        return projection.map(filmProjection -> filmRepository.findById(filmProjection.getFilm().getId())).orElse(null);
    }
    @Override
    public Film findFilmById(Long filmId) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);
        return filmOptional.orElse(null);
    }
}
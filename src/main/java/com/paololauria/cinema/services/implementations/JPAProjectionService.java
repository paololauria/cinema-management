package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Hall;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.model.repository.abstractions.FilmProjectionRepository;
import com.paololauria.cinema.model.repository.abstractions.HallRepository;
import com.paololauria.cinema.services.abstraction.FilmProjectionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class JPAProjectionService implements FilmProjectionService {
    private final FilmProjectionRepository filmProjectionRepository;
    public JPAProjectionService(FilmProjectionRepository filmProjectionRepository) {
        this.filmProjectionRepository = filmProjectionRepository;
    }

    @Override
    public void createProjection(FilmProjection projection, User user) {
        filmProjectionRepository.save(projection);
    }
    @Override
    public void deleteProjectionById(long projectionId, User user) {
        filmProjectionRepository.deleteById(projectionId);
    }
    @Override
    public void updateProjection(FilmProjection projection, User user) {
        filmProjectionRepository.save(projection);
    }


    @Override
    public Optional<FilmProjection> findFilmProjectionById(Long filmProjectionId) {
        Optional<FilmProjection> op = filmProjectionRepository.findById(filmProjectionId);
        return op;
    }


    @Override
    public List<FilmProjection> getFilmProjectionForToday(){
        LocalDate today = LocalDate.now();
        return filmProjectionRepository.findByProjectionDate(today);
    }
    @Override
    public List<FilmProjection> getFilmProjectionByFilmAndDate(Long filmId, LocalDate date) {
        return filmProjectionRepository.findByFilmIdAndProjectionDate(filmId, date);
    }
    @Override
    public List<FilmProjection> getFilmProjectionNextWeek() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(6);
        return filmProjectionRepository.findByProjectionDateBetween(startDate, endDate);
    }
    @Override
    public List<FilmProjection> findAll() {
        return filmProjectionRepository.findAll();
    }
    @Override
    public List<FilmProjection> findAllProjectionByFilmId(long filmId) {
        return filmProjectionRepository.findAllProjectionsByFilmId(filmId);
    }
    @Override
    public List<FilmProjection> findAllProjections() {
        return filmProjectionRepository.findAll();
    }
    @Override
    public List<FilmProjection> getPastProjections() {
        LocalDate today = LocalDate.now();
        return filmProjectionRepository.findAll()
                .stream()
                .filter(pj -> pj.getProjectionDate().isBefore(today))
                .collect(Collectors.toList());
    }
}
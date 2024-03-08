package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Hall;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.exceptions.EntityNotFoundException;
import com.paololauria.cinema.model.repository.abstractions.FilmProjectionRepository;
import com.paololauria.cinema.model.repository.abstractions.FilmRepository;
import com.paololauria.cinema.model.repository.abstractions.HallRepository;
import com.paololauria.cinema.services.abstraction.CinemaService;
import com.paololauria.cinema.services.abstraction.FilmProjectionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class JPAProjectionService implements FilmProjectionService {
    private final FilmProjectionRepository filmProjectionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;
    private final CinemaService cinemaService;
    public JPAProjectionService(FilmProjectionRepository filmProjectionRepository, FilmRepository filmRepository, HallRepository hallRepository, CinemaService cinemaService) {
        this.filmProjectionRepository = filmProjectionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
        this.cinemaService = cinemaService;
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
    public void generateDailyProjections() {
        List<Film> allFilms = filmRepository.findAll();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(6);

        for (Film film : allFilms) {
            for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
                List<FilmProjection> existingProjections = filmProjectionRepository.findByFilmIdAndProjectionDate(film.getId(), date);
                if (existingProjections.isEmpty()) {
                    try {
                        FilmProjection newProjection = createNewProjection(film, date);
                        filmProjectionRepository.save(newProjection);
                        break;
                    } catch (Exception e) {

                    }
                }
            }
        }
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
    public FilmProjection createNewProjection(Film film, LocalDate date) {
        List<Hall> availableHalls = hallRepository.findAll();

        if (availableHalls.isEmpty()) {
            throw new RuntimeException("Not Hall available");
        }

        List<LocalTime> projectionTimes = List.of(LocalTime.of(15, 0), LocalTime.of(19, 0), LocalTime.of(21, 0));
        Double ticketPrice = 10.0;

        for (Hall selectedHall : availableHalls) {
            long existingProjectionsCount = filmProjectionRepository.countByHallAndProjectionDate(selectedHall, date);

            if (existingProjectionsCount < 3) {
                List<FilmProjection> newProjections = projectionTimes.stream()
                        .map(time -> {
                            boolean projectionExists = filmProjectionRepository.existsByFilmAndHallAndProjectionDateAndProjectionTimes(film, selectedHall, date, time);

                            if (!projectionExists) {
                                FilmProjection newProjection = new FilmProjection();
                                newProjection.setFilm(film);
                                newProjection.setHall(selectedHall);
                                newProjection.setProjectionTimes(time);
                                newProjection.setProjectionDate(date);
                                newProjection.setTicketPrice(ticketPrice);

                                return newProjection;
                            } else {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                filmProjectionRepository.saveAll(newProjections);

                if (!newProjections.isEmpty()) {
                    return newProjections.get(0);
                }
            }
        }

        throw new RuntimeException("Impossibile creare nuove proiezioni per il film " + film.getTitle() + " nella data " + date);
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
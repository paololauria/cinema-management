package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public interface FilmProjectionRepository extends JpaRepository<FilmProjection,Long> {
    @Query("""
            SELECT p.hall FROM FilmProjection p WHERE p.id =:id
            """)
    List<FilmProjection> findByHall(long id);
    @Query("SELECT fp FROM FilmProjection fp WHERE fp.film.id = :filmId")
    List<FilmProjection> findAllProjectionsByFilmId(Long filmId);
    List<FilmProjection>findByProjectionDate(LocalDate projectionDate);
    List<FilmProjection> findByProjectionDateBetween(LocalDate startDate, LocalDate endDate);
    List<FilmProjection> findByFilmIdAndProjectionDate(Long filmId, LocalDate date);

    long countByHallAndProjectionDate(Hall hall, LocalDate date);
    boolean existsByFilmAndHallAndProjectionDateAndProjectionTimes(Film film, Hall hall, LocalDate date, LocalTime time);
}

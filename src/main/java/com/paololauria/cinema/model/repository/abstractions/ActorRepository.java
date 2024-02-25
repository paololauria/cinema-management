package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT DISTINCT a FROM Actor a JOIN a.performances p JOIN p.film f WHERE f.id = :filmId")
    List<Actor> findByFilmIdEquals(long filmId);
}

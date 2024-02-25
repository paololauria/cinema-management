package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("""
            SELECT f FROM Film f JOIN f.feedbackList fb 
            GROUP BY f ORDER BY AVG(fb.rating) DESC LIMIT 10
                """)
    List<Film> findTop10ByAverage();
    Film findById(long filmId);
    List<Film> findByGenresGenreName(String genreName);
    List<Film> findByTitleContainingIgnoreCase(String title);


}

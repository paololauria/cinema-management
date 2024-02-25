package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserId(long userId);
    List<Feedback> findByFilmId(long filmId);
}

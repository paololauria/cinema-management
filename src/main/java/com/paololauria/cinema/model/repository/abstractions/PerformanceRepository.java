package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findAllByFilmId(long filmId);
}

package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface HallRepository extends JpaRepository<Hall, Long> {
    @Query("""
            SELECT p.hall FROM FilmProjection p WHERE p.id =:id
            """)
    Hall findByProjectionId(long id);

}

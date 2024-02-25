package com.paololauria.cinema.model.repository.abstractions;
import com.paololauria.cinema.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

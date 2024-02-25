package com.paololauria.cinema.services.abstraction;
import com.paololauria.cinema.model.entities.User;
import java.util.List;
import java.util.Optional;
public interface UserService {
    List<User> findAllUser();
    Optional<User> findById(Long id);
    void createUser(User newUser, User user);
    void deleteUserById(long userId, User user);
    void updateUser(User updateUser, User user);
}

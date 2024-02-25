package com.paololauria.cinema.services.implementations;
import com.paololauria.cinema.model.entities.User;
import com.paololauria.cinema.model.repository.abstractions.UserRepository;
import com.paololauria.cinema.services.abstraction.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class JPAUserService implements UserService {
    private final UserRepository userRepository;
    public JPAUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User newUser, User user) {
        userRepository.save(newUser);
    }
    @Override
    public void deleteUserById(long userId, User user) {
        userRepository.deleteById(userId);
    }
    @Override
    public void updateUser(User updateUser, User user) {
        userRepository.save(updateUser);
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
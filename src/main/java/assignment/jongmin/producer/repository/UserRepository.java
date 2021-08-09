package assignment.jongmin.producer.repository;

import assignment.jongmin.producer.domain.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> findByStatus(String status);

    List<User> findUsers();

    User findOne(Long id);
}

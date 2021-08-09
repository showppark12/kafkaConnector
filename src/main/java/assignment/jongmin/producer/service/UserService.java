package assignment.jongmin.producer.service;

import assignment.jongmin.producer.domain.User;
import assignment.jongmin.producer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findUsers(){
        return userRepository.findUsers();
    }

    public List<User> findUsersByStatus(String status){
        return userRepository.findByStatus(status);
    }

    @Transactional
    public void updateStatusDateTime(Long userId){
        User user = userRepository.findOne(userId);
        user.setStatus("Y");
        user.setUpdated_dt(LocalDateTime.now());
    }
}

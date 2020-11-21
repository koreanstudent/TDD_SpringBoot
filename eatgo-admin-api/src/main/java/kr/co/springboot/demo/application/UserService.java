package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    public User addUser(String email, String name) {

        User user = User.builder().email(email).name(name).level(1L).build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {

        // 예외처리리
       User user = userRepository.findById(id).orElse(null);

       user.setName(name);
       user.setEmail(email);
       user.setLevel(level);
        return user;
    }

    public User deactiveUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        user.deativate();
        return user;
    }
}

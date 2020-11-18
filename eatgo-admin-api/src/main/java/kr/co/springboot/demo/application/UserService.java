package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return null;
    }
}

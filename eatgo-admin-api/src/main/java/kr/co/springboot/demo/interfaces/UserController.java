package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.UserService;
import kr.co.springboot.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // user list
    // user create

    @GetMapping("/users")
    public List<User> list(){


        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        User user = userService.addUser(resource.getEmail(), resource.getName());
        String url="/users/" + user.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }
}

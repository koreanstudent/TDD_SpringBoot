package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.UserService;
import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();
        User user = userService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(user.getId(), user.getName());




        SessionResponseDto sessionDto = SessionResponseDto.builder().accessToken(accessToken).build();

        String uri ="/session";
        return ResponseEntity.created(new URI(uri)).body(sessionDto);
    }
}

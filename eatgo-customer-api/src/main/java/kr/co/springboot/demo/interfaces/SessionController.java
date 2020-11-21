package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {
        String accessToken = "ACCESSTOKEN";
        SessionResponseDto sessionDto = SessionResponseDto.builder().accessToken(accessToken).build();

        String email = resource.getEmail();
        String password = resource.getPassword();
        userService.authenticate(email, password);

        String uri ="/session";
        return ResponseEntity.created(new URI(uri)).body(sessionDto);
    }
}

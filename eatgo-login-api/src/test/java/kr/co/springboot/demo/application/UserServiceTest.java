package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {


    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }



    @Test
    public void authenticateWithValidAttributes() {

        String email ="test@naver.com";
        String password ="test";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        User user = userService.authenticate(email, password);

        assertThat(user.getEmail(), is(email));
    }


    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotExistedEmail() {

        String email ="test@naver.com";
        String password ="test";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.empty());
        userService.authenticate(email, password);

    }

    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword() {

        String email ="test@naver.com";
        String password ="x";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        userService.authenticate(email, password);

    }
}
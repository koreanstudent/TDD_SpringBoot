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
    public void registerUser() {

        String email ="test@naver.com";
        String name ="test";
        String password ="test";
        userService.registerUser(email,name,password);

        verify(userRepository).save(any());
    }

    @Test(expected = EmailExistedException.class)
    public void registerUserWithExistedEmail() {


        String email ="test@naver.com";
        String name ="test";
        String password ="test";
        User user = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        userService.registerUser(email,name,password);

//        verify(userRepository).save(any());
    }





}
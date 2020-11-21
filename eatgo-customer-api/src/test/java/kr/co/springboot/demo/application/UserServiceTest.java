package kr.co.springboot.demo.application;

import kr.co.springboot.demo.application.UserService;
import kr.co.springboot.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {


    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void registerUser() {

        String email ="test@naver.com";
        String name ="test";
        String password ="test";
        userService.registerUser(email,name,password);

        verify(userRepository).save(any());
    }
}
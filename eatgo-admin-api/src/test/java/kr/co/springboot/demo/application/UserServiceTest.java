package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.CategoryRepository;
import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    private UserService userservice;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userservice = new UserService(userRepository);
    }



    @Test
    public void getUsers() {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .email("test@naver.com")
                .name("테스트")
                .level(1L)
                .build());


        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users =userservice.getUsers();
        User user = users.get(0);

        assertThat(user.getName(), is("테스트"));
    }
}
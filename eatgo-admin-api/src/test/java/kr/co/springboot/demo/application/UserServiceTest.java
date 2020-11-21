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
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    @Test
    public void addUser() {
        String email = "test@naver.com";
        String name = "admin";

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userservice.addUser(email, name);

        assertThat(user.getName(),is(name));
    }
    @Test
    public void updateUser() {
        Long id =1004L;
        String email = "test@naver.com";
        String name = "admin";
        Long level =100L;
        User mockUser = User.builder().id(id).name(name).email(email).level(1L).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userservice.updateUser(id, email, name, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is("admin"));
        assertThat(user.isAdmin(), is(true));


    }

    @Test
    public void deactiveUser() {

        Long id =1004L;
        String email = "testadmin@naver.com";
        String name = "admintest";
        Long level =100L;
        User mockUser = User.builder().id(id).name(name).email(email).level(level).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userservice.deactiveUser(1004L);

        verify(userRepository.findById(1004L));

        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(true));
        assertThat(user.isActive(), is(false));
    }


}
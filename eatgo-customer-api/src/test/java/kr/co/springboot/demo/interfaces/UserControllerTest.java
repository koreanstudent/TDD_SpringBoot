package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.UserService;
import kr.co.springboot.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {

        String email ="test@naver.com";
        String name ="test";
        String password ="test";

        User mockUser =User.builder().id(1004L).email(email).name(name).password(password).build();
        given(userService.registerUser("test@naver.com", "test", "test")).willReturn(mockUser);
        
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@naver.com\", \"name\":\"test\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/users/1004"));

        verify(userService).registerUser(eq("test@naver.com"),eq("test"),eq("test"));
    }
}
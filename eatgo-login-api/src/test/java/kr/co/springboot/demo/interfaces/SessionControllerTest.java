package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.EmailNotExistedException;
import kr.co.springboot.demo.application.PasswordWrongException;
import kr.co.springboot.demo.application.UserService;
import kr.co.springboot.demo.domain.User;
import kr.co.springboot.demo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void create() throws Exception {

        Long id =1004L;
        String name ="test";
        User mockUser = User.builder().id(id).name(name).level(1L).build();

        given(userService.authenticate("test@naver.com","test")).willReturn(mockUser);

        given(jwtUtil.createToken(id,name,null)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@naver.com\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"")));

        verify(userService).authenticate(eq("test@naver.com"), eq("test"));


    }

    @Test
    public void createRestaurantOwner() throws Exception {

        Long id =1004L;
        String name ="test";
        User mockUser = User.builder().id(id).name(name).level(50L).restaurantId(369L).build();

        given(userService.authenticate("test@naver.com","test")).willReturn(mockUser);

        given(jwtUtil.createToken(id,name,369L)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@naver.com\", \"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"")));

        verify(userService).authenticate(eq("test@naver.com"), eq("test"));


    }

    @Test
    public void createWithNotExistedEmail() throws Exception {

        given(userService.authenticate("test@naver.com","test")).willThrow(EmailNotExistedException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@naver.com\", \"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("test@naver.com"), eq("test"));


    }

    @Test
    public void createWithWrongPassword() throws Exception {

        given(userService.authenticate("test@naver.com","x")).willThrow(PasswordWrongException.class);
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@naver.com\", \"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("test@naver.com"), eq("x"));
    }



}
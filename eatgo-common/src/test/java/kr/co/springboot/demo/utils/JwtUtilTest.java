package kr.co.springboot.demo.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Test
    public void createToken() {
        String secret ="12304812409182490812048";
        JwtUtil jwtUtil = new JwtUtil(secret);

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }
}
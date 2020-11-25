package kr.co.springboot.demo.utils;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private static final String SECRET ="012345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @Before
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.SaTSZjReMkkVbBrtgM6QtIDiZ-MixzbhxnvTMV0BzBY";
        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class),is("John"));
        assertThat(claims.get("name"), is("John"));
    }
}
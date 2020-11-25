package kr.co.springboot.demo.filters;

import io.jsonwebtoken.Claims;
import kr.co.springboot.demo.utils.JwtUtil;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    // 실제로 JWT 분석

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);
        if(authentication != null){
            SecurityContext context = SecurityContextHolder.getContext();

            context.setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null){
            return null;
        }

        // jwtutill에서 calims 얻기
        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));

        Authentication authenication = new UsernamePasswordAuthenticationToken(claims, null);
        return authenication;
    }
}

package kr.co.springboot.demo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequestDto {

    private String email;
    private String password;
}

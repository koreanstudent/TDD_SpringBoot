package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.EmailNotExistedException;
import kr.co.springboot.demo.application.PasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailNotExistedException.class)
    public String handleEmailNotExisted() {
        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PasswordWrongException.class)
    public String handlePasswordWrong() {
        return "{}";
    }
}

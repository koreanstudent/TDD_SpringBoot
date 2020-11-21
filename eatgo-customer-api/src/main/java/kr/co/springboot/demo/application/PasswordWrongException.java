package kr.co.springboot.demo.application;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException(){
        super("Password is wrong");
    }
}

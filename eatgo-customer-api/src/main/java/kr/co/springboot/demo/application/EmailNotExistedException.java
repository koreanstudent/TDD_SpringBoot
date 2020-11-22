package kr.co.springboot.demo.application;

public class EmailNotExistedException extends RuntimeException {
    EmailNotExistedException(String email){
        super("Email not found" + email);
    }
}

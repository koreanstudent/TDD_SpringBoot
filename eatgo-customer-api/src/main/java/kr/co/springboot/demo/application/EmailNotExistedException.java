package kr.co.springboot.demo.application;

public class EmailNotExistedException extends RuntimeException {
    EmailNotExistedException(){
        super("Email not found");
    }
}

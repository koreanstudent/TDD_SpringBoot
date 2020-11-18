package kr.co.springboot.demo.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private Long level;

    public boolean isAdmin() {
        return level >=100;
    }
}

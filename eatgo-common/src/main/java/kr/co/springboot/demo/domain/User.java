package kr.co.springboot.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public boolean isActive() {
        return level > 0;
    }
    public void deativate(){
        level = 0L;
    }
}

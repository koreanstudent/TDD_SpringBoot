package kr.co.springboot.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String password;

    private String email;

    private String name;

    private Long level;

    private Long restaurantId;

    public boolean isAdmin() {
        return level >=100;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deativate(){
        level = 0L;
    }

    public void setRestaurantId(Long restaurantId){
        level = 50L;
        this.restaurantId = restaurantId;
    }
    public boolean isRestaurantOwner() {
        return level == 50L;
    }

}

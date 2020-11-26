package kr.co.springboot.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;

    private String name;

    private Integer score;

    @Getter
    private String description;


}

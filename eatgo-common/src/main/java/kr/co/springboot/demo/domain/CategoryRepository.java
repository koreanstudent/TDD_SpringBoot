package kr.co.springboot.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    
    Category save(Category category); // 생략 가능
}

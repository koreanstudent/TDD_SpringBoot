package kr.co.springboot.demo.domain;

import org.junit.Assert;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void creation() {
        Category category = Category.builder().name("Korean Food").build();
        assertThat(category.getName(), is("Korean Food"));
    }

}
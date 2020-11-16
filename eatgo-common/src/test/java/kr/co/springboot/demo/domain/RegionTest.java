package kr.co.springboot.demo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {

    @Test
    public void creation() {
        Region region = Region.builder().name("서울").build();

        assertThat(region.getName(), is("서울"));
    }
}
package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.Region;
import kr.co.springboot.demo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class RegionServiceTest {

    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions() {

        List<Region> mockregions = new ArrayList<>();
        mockregions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(mockregions);

        List<Region> regions =regionService.getRegions();

        Region region = regions.get(0);
        assertThat(region.getName(),is("Seoul"));
    }

}
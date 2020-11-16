package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.Category;
import kr.co.springboot.demo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class CategoryServiceTest {

    private CategoryService CategoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CategoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories() {

        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories =CategoryService.getCategories();

        Category category = categories.get(0);
        assertThat(category.getName(),is("Korean Food"));
    }

}
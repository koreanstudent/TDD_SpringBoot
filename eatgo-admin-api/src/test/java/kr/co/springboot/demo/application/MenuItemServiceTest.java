package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.MenuItem;
import kr.co.springboot.demo.domain.MenuItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;


public class MenuItemServiceTest {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepsitory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepsitory);
    }

    @Test
    public void getMenuItems() {
        List<MenuItem> mockMenuItems = new ArrayList<>();

        mockMenuItems.add(MenuItem.builder().name("Kimchi").build());

        given(menuItemRepsitory.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);

        MenuItem menuItem = menuItems.get(0);

        assertThat(menuItem.getName(),is("Kimchi"));
    }

    @Test
    public void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<>();
        
        menuItems.add(MenuItem.builder()
        .name("Kimchi")
        .build());

        menuItemService.bulkUpdate(1L, menuItems);
        
    }

}
package ko.co.springbot.demo.application;

import kr.co.springboot.demo.application.MenuItemService;
import kr.co.springboot.demo.domain.MenuItem;
import kr.co.springboot.demo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    public void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<>();
        
        menuItems.add(MenuItem.builder()
        .name("Kimchi")
        .build());

        menuItemService.bulkUpdate(1L, menuItems);
        
    }

}
package kr.co.springboot.demo.interfaces;

import kr.co.springboot.demo.application.MenuItemService;
import kr.co.springboot.demo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuItems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems) {

        menuItemService.bulkUpdate(restaurantId,menuItems);

        return "";
    }
}

package kr.co.springboot.demo.application;

import kr.co.springboot.demo.domain.MenuItem;
import kr.co.springboot.demo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {


    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(long restaurantId) {

        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {

        for(MenuItem menuItem :menuItems){
            if(menuItem.isDestroy()){
                menuItemRepository.deleteById(menuItem.getId());
                return;
            }
            menuItem.setRestaurantId(restaurantId);

            menuItemRepository.save(menuItem);
        }

    }


}

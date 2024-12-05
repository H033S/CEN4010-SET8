package fiu.cen.menug.service;

import fiu.cen.menug.model.entity.MenuItem;
import fiu.cen.menug.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public Optional<MenuItem> findById(String menuItemId){
        return menuItemRepository.findById(menuItemId);
    }

    public MenuItem save(MenuItem menuItem){
        return menuItemRepository.save(menuItem);
    }

    public boolean existsById(String menuItemId){
        return menuItemRepository.existsById(menuItemId);
    }

    @Transactional
    public void deleteById(String menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }
}

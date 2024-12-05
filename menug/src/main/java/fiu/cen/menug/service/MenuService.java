package fiu.cen.menug.service;

import fiu.cen.menug.model.entity.Menu;
import fiu.cen.menug.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Optional<Menu> findMenuById(String id) {
        return menuRepository.findById(id);
    }

    public boolean existById(String menuId) {
        return menuRepository.existsById(menuId);
    }

    public void save(Menu menu) {
        menuRepository.save(menu);
    }
}
